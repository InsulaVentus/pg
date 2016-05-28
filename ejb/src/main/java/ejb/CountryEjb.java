package ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Stateless
@Remote(Countries.class)
public class CountryEjb implements Countries, Serializable {

    public List<String> getCountries() {
        return Arrays.asList("Norway", "Turkmenistan");
    }

    @Override
    public void clear() {
        System.out.println("Removing instance from container");
    }
}
