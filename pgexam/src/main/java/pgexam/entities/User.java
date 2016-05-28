package pgexam.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by ng20 on 27.05.2016.
 */
@Entity
@Table(name = "USER")
@NamedQuery(name = User.DELETE_ALL, query = "DELETE FROM User")
public class User {

    public static final String DELETE_ALL = "delete_all";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    private String country;

    public User(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getMiddleName(){
        return middleName;
    }
    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getCountry(){
        return country;
    }
    public void setCountry(String country){
        this.country = country;
    }

}
