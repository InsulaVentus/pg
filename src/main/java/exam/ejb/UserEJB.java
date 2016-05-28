package exam.ejb;

import exam.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Stateless
public class UserEJB implements Serializable {

    @PersistenceContext(name = "pg6100_exam")
    private EntityManager em;

    public UserEJB() {
    }

    public User getUser(String name) {
        return em.find(User.class, name);
    }

    public User getPassword(String password) {
        return em.find(User.class, password);
    }

    public String getUserByUserName(String name) {
        return em.createQuery("select u from User u where u.name=name").getSingleResult().toString();
    }

    public String getSinglPassword(String passowrd) {
        return em.createQuery("select u from User u where u.password=password").getSingleResult().toString();

    }

    /**
     * @return {@code true} if a user with the given password exists
     */
    public boolean login(String name, String password) {
        if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        String userName = getUserByUserName(name);
        String userPassword = getSinglPassword(password);
        return !(userName == null || userPassword == null);
    }

    /**
     * @return {@code false} if for any reason it was not possible to create the user
     */
    public boolean createUser(String username, String password, String firstName, String lastName,
                              String middleName, String country) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        User user = getUser(username);
        if (user != null) {
            return false;
        }
        user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        user.setCountry(country);
        em.persist(user);
        return true;
    }
}
