package pgexam.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String password;

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

}
