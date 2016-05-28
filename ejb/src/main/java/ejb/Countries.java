package ejb;

import javax.ejb.Remove;
import java.util.List;

public interface Countries {

    List<String> getCountries();

    @Remove
    void clear();

}
