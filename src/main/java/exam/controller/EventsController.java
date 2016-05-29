package exam.controller;

import exam.ejb.CountryEjb;
import exam.ejb.EventEJB;
import exam.entities.Event;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class EventsController implements Serializable {

    @Inject
    private EventEJB eventEJB;

    private String formTitle;
    private String formCountry;
    private String formLocation;
    private String formDescription;
    private boolean isCreated;

    public String eventCanlcel(){
        return "home.xhtml";
    }

    public String createNew(){
        boolean createt = eventEJB.createEvent(formTitle,formCountry,formLocation,formDescription);
        if(createt){
            isCreated = true;
            return "home.xhtml?faces-redirect=true";
        }else {
            return "newEvent.xhtml";
        }
    }
    public List<Event> getEvents(){
        if(eventEJB.allEvents().isEmpty()){
           isCreated = false;
        }
        return eventEJB.allEvents();
    }

    public List<Event> getEventsByCountry(String country) {
        return eventEJB.getEventByCountry(country);
    }

    public Event getEventById(String id) {
        List events = eventEJB.getEventById(id);
        if (events == null || events.isEmpty()) {
            return null;
        }
        return (Event) events.get(0);
    }

    public boolean isValidCountry(String queryParam) {
        return getAllCountries().contains(queryParam);
    }

    public List<String> getAllCountries(){
        CountryEjb countryEjb = new CountryEjb();
        return countryEjb.getCountries();
    }
    public String getFormCountry() {
        return formCountry;
    }

    public void setFormCountry(String formCountry) {
        this.formCountry = formCountry;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getFormLocation() {
        return formLocation;
    }

    public void setFormLocation(String formLocation) {
        this.formLocation = formLocation;
    }

    public String getFormDescription() {
        return formDescription;
    }

    public void setFormDescription(String formDescription) {
        this.formDescription = formDescription;
    }

    public boolean isCreated() {
        return isCreated;
    }

}
