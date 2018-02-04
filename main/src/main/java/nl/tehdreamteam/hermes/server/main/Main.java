package nl.tehdreamteam.hermes.server.main;

import nl.tehdreamteam.hermes.server.core.HermesServer;
import nl.tehdreamteam.hermes.server.core.Service;
import nl.tehdreamteam.hermes.server.core.ServiceLocator;
import nl.tehdreamteam.hermes.server.core.grizzly.GrizzlyHermesServer;
import nl.tehdreamteam.hermes.server.core.handler.LocateServiceHandler;
import nl.tehdreamteam.hermes.server.core.service.HermesService;
import org.glassfish.grizzly.http.server.HttpHandler;

import java.io.IOException;
import java.util.Optional;

public class Main {

    public static void main(String... args) throws IOException {
        HermesServer server = new GrizzlyHermesServer(1201);

        /*server.addHandler(new HttpHandler() {
            @Override
            public void service(Request request, Response response) throws Exception {
                final SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
                final String date = format.format(new Date(System.currentTimeMillis()));
                response.setContentType("text/plain");
                response.setContentLength(date.length());
                response.getWriter().write(date);
            }
        });*/

        ServiceLocator locator = new ServiceLocator() {
            @Override
            public Optional<Service> getService(String name) {
                if (name.equals("Hans")) {
                    return Optional.of(new HermesService("localhost", 1202));
                }
                return Optional.empty();
            }
        };

        HttpHandler locateHandler = new LocateServiceHandler(locator);

        server.addHandler(locateHandler);

        server.start();

        System.in.read();
        server.stop();
    }
}
