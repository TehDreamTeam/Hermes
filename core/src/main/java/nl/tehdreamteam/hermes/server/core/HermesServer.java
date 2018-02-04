package nl.tehdreamteam.hermes.server.core;

import org.glassfish.grizzly.http.server.HttpHandler;

import java.io.IOException;

public interface HermesServer {

    void addHandler(HttpHandler handler);

    void addHandler(HttpHandler handler, String... mappings);

    void start() throws IOException;

    void stop() throws IOException;

    boolean isActive();
}
