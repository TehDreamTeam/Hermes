package nl.tehdreamteam.hermes.server.core.handler;

import nl.tehdreamteam.hermes.server.core.Service;
import nl.tehdreamteam.hermes.server.core.ServiceLocator;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.util.Optional;

public class LocateServiceHandler extends HttpHandler {

    private static final String serviceName = "serviceName";

    private ServiceLocator locator;

    public LocateServiceHandler(ServiceLocator locator) {
        this.locator = locator;
    }

    public LocateServiceHandler(String name, ServiceLocator locator) {
        super(name);
        this.locator = locator;
    }

    @Override
    public void service(Request request, Response response) throws Exception {
        Optional<String> optName = Optional.ofNullable(request.getParameter(serviceName));

        if (optName.isPresent()) {
            String name = optName.get();
            Optional<Service> optService = locator.getService(name);

            if (optService.isPresent()) {
                Service service = optService.get();
                String address = service.getFullAddress();
                response.getWriter().write(address);
            } else {
                String error = "Could not locate service with name: " + name;
                response.setStatus(HttpStatus.NOT_FOUND_404);
                response.getWriter().write(error);
            }
        } else {
            String error = "Missing parameter: " + serviceName;
            response.setStatus(HttpStatus.BAD_REQUEST_400);
            response.getWriter().write(error);
        }
    }
}
