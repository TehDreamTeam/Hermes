package nl.tehdreamteam.hermes.server.core;

import java.util.Optional;

public interface ServiceStore {

    Optional<Service> getService();

    void registerService(Service service);
}
