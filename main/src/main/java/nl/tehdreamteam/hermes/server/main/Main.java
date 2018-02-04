package nl.tehdreamteam.hermes.server.main;

import nl.tehdreamteam.hermes.server.core.HermesServer;
import nl.tehdreamteam.hermes.server.core.ServiceManager;
import nl.tehdreamteam.hermes.server.core.grizzly.GrizzlyHermesServer;
import nl.tehdreamteam.hermes.server.core.handler.LocateServiceHandler;
import nl.tehdreamteam.hermes.server.core.handler.RegisterServiceHandler;
import nl.tehdreamteam.hermes.server.core.service.HermesService;
import nl.tehdreamteam.hermes.server.core.service.HermesServiceManager;
import org.glassfish.grizzly.http.server.HttpHandler;

import java.io.IOException;

public class Main {

    public static void main(String... args) throws IOException {
        HermesServer server = new GrizzlyHermesServer("/hermes/", 1201);

        ServiceManager serviceManager = new HermesServiceManager();
        serviceManager.registerService("Hans", new HermesService("www.kippetjes.nl", 8080));

        HttpHandler locateHandler = new LocateServiceHandler(serviceManager);
        HttpHandler registerHandler = new RegisterServiceHandler(serviceManager);

        server.addHandler(registerHandler, "/hermes/register");
        server.addHandler(locateHandler, "/hermes/locate");

        server.start();

        System.in.read();
        server.stop();
    }
}
