package ejb;

import org.junit.Ignore;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.StringReader;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Allows for testing of CountryEjb locally - without deploy.
 */
@Ignore
public class RestTest {

    private static final String RESOURCE_URL = "http://restcountries.eu/rest/v1/all";

    @Test
    public void javaxRestTest() {
        URI uri = UriBuilder.fromUri(RESOURCE_URL).port(80).build();
        Client client = ClientBuilder.newClient();

        Response response = client.target(uri).request("application/json").get();
        JsonArray jsonValues = Json.createReader(new StringReader(response.readEntity(String.class))).readArray();

        List<String> countries = jsonValues.stream().map(value -> ((JsonObject) value).getString("name")).collect(Collectors.toList());
        System.out.println("Found as many as " + countries.size() + " countries");
    }
}
