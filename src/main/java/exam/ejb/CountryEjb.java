package exam.ejb;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.io.StringReader;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CountryEjb implements Countries, Serializable {

    private static final String RESOURCE_URL = "http://restcountries.eu/rest/v1/all";

    public List<String> getCountries() {
        URI uri = UriBuilder.fromUri(RESOURCE_URL).port(80).build();
        Client client = ClientBuilder.newClient();

        Response response = client.target(uri).request("application/json").get();
        JsonArray jsonValues = Json.createReader(new StringReader(response.readEntity(String.class))).readArray();
        return jsonValues.stream().map(value -> ((JsonObject) value).getString("name")).collect(Collectors.toList());
    }
}
