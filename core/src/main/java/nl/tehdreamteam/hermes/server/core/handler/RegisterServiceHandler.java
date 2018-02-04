package nl.tehdreamteam.hermes.server.core.handler;

import nl.tehdreamteam.hermes.server.core.ServiceManager;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.util.HttpStatus;

public class RegisterServiceHandler extends HttpHandler {

    ServiceManager manager;

    public RegisterServiceHandler(ServiceManager manager) {
        this.manager = manager;
    }

    public RegisterServiceHandler(String name, ServiceManager manager) {
        super(name);
        this.manager = manager;
    }

    @Override
    public void service(Request request, Response response) {
        response.setStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED_505);
    }
}
