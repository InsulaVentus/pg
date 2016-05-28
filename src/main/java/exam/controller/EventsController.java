package exam.controller;

import exam.ejb.CountryEjb;
import exam.ejb.EventEJB;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ng20 on 28.05.2016.
 */
@Named
@SessionScoped
public class EventsController implements Serializable {

    @Inject
    private EventEJB eventEJB;

    private String formTitle;
    private String formCountry;
    private String formLocation;
    private String formDescription;

    public String eventCanlcel(){
        return "home.xhtml";
    }

    public String createNew(){
        boolean createt = eventEJB.createEvent(formTitle,formCountry,formLocation,formDescription);
        if(createt){
            return "home.xhtml?faces-redirect=true";
        }else {
            return "newEvent.xhtml";
        }
    }
    public List<String> getAllCountry(){
        List<String> list = new ArrayList<String>();
        CountryEjb countryEjb = new CountryEjb();
        list = countryEjb.getCountries();
        return list;
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
}
