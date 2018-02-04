package nl.tehdreamteam.hermes.server.core.service;

import nl.tehdreamteam.hermes.server.core.Service;
import nl.tehdreamteam.hermes.server.core.ServiceStore;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class HermesServiceStore implements ServiceStore {

    private Collection<Service> services;

    public HermesServiceStore() {
        this.services = new LinkedList<>();
    }

    @Override
    public Optional<Service> getService() {
        try {
            return Optional.of(services.iterator().next());
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void registerService(Service service) {
        services.add(service);
        //TODO make sure this doesn't raise exceptions.
    }
}
