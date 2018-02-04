package nl.tehdreamteam.hermes.server.core;

import java.util.Optional;

public interface ServiceLocator {

    Optional<Service> getService(String name);
}
