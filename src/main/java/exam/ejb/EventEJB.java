package exam.ejb;

import exam.entities.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class EventEJB implements Serializable {
    @PersistenceContext(name = "pg6100_exam")
    private EntityManager em;

    public EventEJB(){

    }

    /**
     *
     * @param title
     * @param country
     * @param location
     * @param description
     * @return {@code false} if for any reason it was not possible to create the user
     */
    public boolean createEvent(String title, String country, String location, String description){
        if(title == null || title.isEmpty()|| country == null || country.isEmpty()
                || location == null || location.isEmpty() || description  == null || description.isEmpty()){
            return false;
        }
        Event event = new Event();
        event.setTitle(title);
        event.setCountry(country);
        event.setLocation(location);
        event.setDescription(description);
        em.persist(event);

        return true;
    }

    public List allEvents(){
        return em.createQuery("select u from Event u").getResultList();
    }

    public List getEventByCountry(String country) {
        return em.createQuery("select u from Event u where u.country = :country")
                .setParameter("country", country)
                .getResultList();
    }

    public List getEventById(String id) {
        return em.createQuery("select u from Event u where u.id = :id")
                .setParameter("id", id)
                .getResultList();
    }
}
