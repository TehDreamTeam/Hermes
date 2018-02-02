package nl.tehdreamteam.hermes.core.grizzly;

import nl.tehdreamteam.hermes.core.HermesServer;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;

import java.io.IOException;

public class GrizzlyHermesServer implements HermesServer {

    private HttpServer server;

    public GrizzlyHermesServer(int port) {
        server = HttpServer.createSimpleServer("/hermes", port);
    }

    @Override
    public void addHandler(HttpHandler handler) {
        addHandler(handler, "/");
    }

    @Override
    public void addHandler(HttpHandler handler, String... mappings) {
        server.getServerConfiguration().addHttpHandler(handler, mappings);
    }

    @Override
    public void start() throws IOException {
        server.start();
    }

    @Override
    public void stop() {
        server.shutdownNow();
    }

    @Override
    public boolean isActive() {
        return server.isStarted();
    }
}
