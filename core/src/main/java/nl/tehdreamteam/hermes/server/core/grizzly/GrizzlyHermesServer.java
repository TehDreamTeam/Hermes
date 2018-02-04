package nl.tehdreamteam.hermes.server.core.grizzly;

import nl.tehdreamteam.hermes.server.core.HermesServer;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;

import java.io.IOException;

public class GrizzlyHermesServer implements HermesServer {

    private HttpServer server;
    private String docRoot;

    public GrizzlyHermesServer(String docRoot, int port) {
        this.docRoot = docRoot;
        server = HttpServer.createSimpleServer(docRoot, port);
    }

    @Override
    public void addHandler(HttpHandler handler) {
        addHandler(handler, docRoot);
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
