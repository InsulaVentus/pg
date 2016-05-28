package ejb;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface Countries {

    List<String> getCountries();

}
