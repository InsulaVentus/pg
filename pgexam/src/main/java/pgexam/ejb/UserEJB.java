package pgexam.ejb;

import pgexam.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by ng20 on 27.05.2016.
 */
@Stateless
public class UserEJB implements Serializable {

    @PersistenceContext(name = "pg6100_exam")
    private EntityManager em;

    public UserEJB(){
    }

    public User getUser(String name){
        return em.find(User.class, name);
    }
    public User getPassword(String password){ return em.find(User.class, password);
    }
    /**
     *
     * @param name
     * @param password
     * @return  {@code true} if a user with the given password exists
     */
    public boolean login(String name, String password) {
        if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        User user = getUser(name);
        User pass = getPassword(password);
        if (user == null ) {
            return false;
        }
        boolean isOK = pass.equals(user.getPassword());
        return isOK;
    }
}
