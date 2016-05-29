package exam.rest;

import com.google.gson.Gson;
import exam.controller.EventsController;
import exam.entities.Event;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

@Stateless
@Path("/events")
public class EventService {

    @Inject
    private EventsController controller;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getEventAsXml(@QueryParam("id") String id) {

        if (id == null) {
            return Response.status(400).entity("Please provide an id").build();
        }

        Event event = controller.getEventById(id);
        if (event == null) {
            return Response.status(404).entity("Did not find any events with id: " + id).build();
        }
        return Response.ok(createEventXml(event), MediaType.APPLICATION_XML).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventAsJson(@QueryParam("id") String id) {

        if (id == null) {
            return Response.status(400).entity("Please provide an id").build();
        }

        Event event = controller.getEventById(id);
        if (event == null) {
            return Response.status(404).entity("Did not find any events with id: " + id).build();
        }

        Gson gson = new Gson();
        return Response.ok(gson.toJson(event), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllEventsAsXml(@QueryParam("country") String country) {

        if (country == null) {
            List<Event> events = controller.getEvents();
            return Response.ok(createEventXml(events.toArray(new Event[events.size()])), MediaType.APPLICATION_XML).build();
        }

        if (!controller.isValidCountry(country)) {
            return Response.status(400).entity("You did not provide a valid country!").build();
        }

        List<Event> events = controller.getEventsByCountry(country);
        return Response.ok(createEventXml(events.toArray(new Event[events.size()])), MediaType.APPLICATION_XML).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEventsAsJson(@QueryParam("country") String country) {

        Gson gson = new Gson();

        if (country == null) {
            List<Event> events = controller.getEvents();
            return Response.ok(gson.toJson(events), MediaType.APPLICATION_JSON).build();
        }

        if (!controller.isValidCountry(country)) {
            return Response.status(400).entity("You did not provide a valid country!").build();
        }
        return Response.ok(gson.toJson(controller.getEventsByCountry(country)), MediaType.APPLICATION_JSON).build();
    }

    public static String createEventXml(Event... events) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        String xml = "";

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElementNS("", "Events");

            for (Event event : events) {
                root.appendChild(createEventXml(document, event));
            }

            DOMImplementationLS lsImpl = (DOMImplementationLS) root.getOwnerDocument().getImplementation();
            LSSerializer serializer = lsImpl.createLSSerializer();
            xml = serializer.writeToString(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    public static Node createEventXml(Document document, Event event) {
        Element eventElement = document.createElement("Event");

        Element id = document.createElement("id");
        id.appendChild(document.createTextNode(event.getId()));
        eventElement.appendChild(id);

        Element title = document.createElement("title");
        title.appendChild(document.createTextNode(event.getTitle()));
        eventElement.appendChild(title);

        Element country = document.createElement("country");
        country.appendChild(document.createTextNode(event.getCountry()));
        eventElement.appendChild(country);

        Element location = document.createElement("location");
        location.appendChild(document.createTextNode(event.getLocation()));
        eventElement.appendChild(location);

        return eventElement;
    }
}
