package ejb;

import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;
import static javax.naming.Context.URL_PKG_PREFIXES;


public class CountryEjbIntegrationTest {

    private static final String LOCALHOST = "127.0.0.1";

    private static final String WILD_FLY_PORT = "8080";

    private static final String EJB = "ejb";

    private static final String APP_NAME = "ejb-module";

    /**
     * Syntax: ejb:${appName}/${moduleName}/${beanName}!${remoteView}
     * appName = name of EAR deployment (or empty for single EJB/WAR deployments)
     * moduleName = name of EJB/WAR deployment
     * beanName = name of the EJB (Simple name of EJB class)
     * remoteView = fully qualified remote interface class
     */
    private static final String EJB_URL =
            EJB + ":/" +
                    APP_NAME + "/" +
                    CountryEjb.class.getSimpleName() + "!" +
                    Countries.class.getName();

    @Test
    public void getAllCountries() {
        try {
            Context remoteEjbContext = createRemoteEjbContext(LOCALHOST, WILD_FLY_PORT);
            Countries countryEjb = (Countries) remoteEjbContext.lookup(EJB_URL);
            countryEjb.getCountries();
        } catch (NamingException e) {
            System.err.println("Error resolving bean");
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.err.println("Resolved EJB is of wrong type");
            e.printStackTrace();
        }
    }

    /**
     * Create Remote EJB Context.
     *
     * @param host host to connect to
     * @param port port to connect to
     * @return remote EJB context
     * @throws NamingException if creating the context fails
     */
    private static Context createRemoteEjbContext(String host, String port) throws NamingException {
        Hashtable<Object, Object> props = new Hashtable<>();

        props.put(INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        props.put(URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        props.put("jboss.naming.client.ejb.context", false);
        props.put("org.jboss.ejb.client.scoped.context", true);

        props.put("endpoint.name", "client-endpoint");
        props.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
        props.put("remote.connections", "default");
        props.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);

        props.put(PROVIDER_URL, "http-remoting://" + host + ":" + port);
        props.put("remote.connection.default.host", host);
        props.put("remote.connection.default.port", port);

        return new InitialContext(props);
    }
}