package nl.tehdreamteam.hermes.server.core.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.tehdreamteam.hermes.server.core.Service;
import nl.tehdreamteam.hermes.server.core.ServiceManager;
import nl.tehdreamteam.hermes.server.core.service.HermesService;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import java.io.IOException;

public class RegisterServiceHandler extends HttpHandler {

    private ServiceManager manager;

    public RegisterServiceHandler(ServiceManager manager) {
        this.manager = manager;
    }

    public RegisterServiceHandler(String name, ServiceManager manager) {
        super(name);
        this.manager = manager;
    }

    @Override
    public void service(Request request, Response response) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;

        try {
            node = mapper.readTree(request.getInputStream());
            System.out.println(node);
        } catch (IOException e) {
            response.sendError(1337, "Je moeder is een slechte JSON.");
        }

        String name = node.get("name").asText();
        String hostname = node.get("hostname").asText();
        int port = node.get("port").asInt();

        Service service = new HermesService(hostname, port);
        manager.registerService(name, service);
    }
}
