package pgexam.controller;

import pgexam.ejb.UserEJB;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

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

    public LogginController(){
    }
    public String logCancel(){

        return "home.xhtml";
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

}
