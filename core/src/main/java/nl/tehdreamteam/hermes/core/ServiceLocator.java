package nl.tehdreamteam.hermes.core;

import java.util.Optional;

public interface ServiceLocator {

    Optional<Service> getService(String name);
}
