package exam.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by ng20 on 28.05.2016.
 */
@Entity
@Table(name = "Event")
@NamedQuery(name = Event.DELETE_ALL, query = "DELETE FROM Event")
public class Event {
    public static final String DELETE_ALL = "delete_all_events";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotNull
    private String title;

    @NotNull
    private String country;

    @NotNull
    private String location;

    @NotNull
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
