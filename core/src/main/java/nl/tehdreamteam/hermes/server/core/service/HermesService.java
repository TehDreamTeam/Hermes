package nl.tehdreamteam.hermes.server.core.service;

import nl.tehdreamteam.hermes.server.core.Service;

public class HermesService implements Service {

    private String hostname;
    private int port;

    public HermesService(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public String getHostname() {
        return this.hostname;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public String getFullAddress() {
        StringBuilder builder = new StringBuilder();

        builder.append("http://");
        builder.append(hostname);
        builder.append(':');
        builder.append(port);
        builder.append("/");

        return builder.toString();
    }
}
