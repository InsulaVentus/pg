package exam.rest;

import exam.controller.EventsController;
import exam.entities.Event;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
        return Response.ok("Værsågod, id = " + id, MediaType.TEXT_HTML).build(); //TODO Transform to XML
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
        return Response.ok("Værsågod, id = " + id, MediaType.TEXT_HTML).build(); //TODO Transform to JSON
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_XML)
    public Response getAllEventsAsXml(@QueryParam("country") String country) {

        if (country == null) {
            List<Event> events = controller.getEvents(); //TODO Transform to XML
            return Response.ok("Here are all the events", MediaType.TEXT_HTML).build();
        }

        if (!controller.isValidCountry(country)) {
            return Response.status(400).entity("You did not provide a valid country!").build();
        }

        List<Event> events = controller.getEventsByCountry(country);  //TODO Transform to XML
        return Response.ok("Here are all events (total: " + events.size() + ") in " + country, MediaType.TEXT_HTML).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEventsAsJson(@QueryParam("country") String country) {

        if (country == null) {
            List<Event> events = controller.getEvents(); //TODO Transform to JSON
            return Response.ok("Here are all the events", MediaType.TEXT_HTML).build();
        }

        if (!controller.isValidCountry(country)) {
            return Response.status(400).entity("You did not provide a valid country!").build();
        }

        List<Event> events = controller.getEventsByCountry(country);  //TODO Transform to JSON
        return Response.ok("Here are all events (total: " + events.size() + ") in " + country, MediaType.TEXT_HTML).build();
    }
}
