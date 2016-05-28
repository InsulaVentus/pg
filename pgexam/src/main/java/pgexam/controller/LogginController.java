package pgexam.controller;

import ejb.CountryEjb;
import pgexam.ejb.UserEJB;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ng20 on 27.05.2016.
 */
@Named
@SessionScoped
public class LogginController implements Serializable {
    @Inject
    private UserEJB userEJB;

    private String formUserName;
    private String formPassword;
    private String registeredUser;
    private String formFirstName;
    private String formLastName;
    private String formMiddleName;
    private String formCounrty;
    public LogginController(){
    }
    public String logCancel(){

        return "home.xhtml";
    }
    public String registerNew(){
        boolean registered = userEJB.createUser(formUserName,formPassword,formFirstName,formLastName,
                formMiddleName,formCounrty);
        if(registered){
            registeredUser = formUserName;
            return "home.xhtml?faces-redirect=true";
        } else {
            return "login.xhtml";
        }
    }
    public List<String> getAllCountry(){
        List<String> list = new ArrayList<String>();
        CountryEjb countryEjb = new CountryEjb();
        list = countryEjb.getCountries();
        return list;
    }
    public String logIn(){
        boolean valid = userEJB.login(formUserName, formPassword);
        if(valid){
            registeredUser = formUserName;
            return "home.xhtml?faces-redirect=true";
        } else {
            return "login.xhtml";
        }
    }
    public String logOut(){
        registeredUser = null;
        return "home.xhtml";
    }
    public boolean isLoggedIn(){
        return registeredUser != null;
    }
    public String getFormUserName() {
        return formUserName;
    }

    public void setFormUserName(String formUserName) {
        this.formUserName = formUserName;
    }

    public String getFormPassword() {
        return formPassword;
    }

    public void setFormPassword(String formPassword) {
        this.formPassword = formPassword;
    }

    public String getFormFirstName(){return formFirstName;}

    public void setFormFirstName(String formFirstName){this.formFirstName = formFirstName;}

    public String getFormLastName(){return formLastName;}

    public void setFormLastName(String formLastName){this.formLastName = formLastName;}

    public String getFormMiddleName(){return formMiddleName;}

    public void setFormMiddleName(String formMiddleName){this.formMiddleName = formMiddleName;}

    public String getFormCounrty(){return formCounrty;}

    public void setFormCounrty(String formCounrty){this.formCounrty = formCounrty;}

    public String getRegisteredUser(){return registeredUser;}


}
