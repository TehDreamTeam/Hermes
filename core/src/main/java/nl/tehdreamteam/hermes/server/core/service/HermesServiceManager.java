package nl.tehdreamteam.hermes.server.core.service;

import nl.tehdreamteam.hermes.server.core.Service;
import nl.tehdreamteam.hermes.server.core.ServiceManager;
import nl.tehdreamteam.hermes.server.core.ServiceStore;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HermesServiceManager implements ServiceManager {

    private Map<String, ServiceStore> serviceMap;

    public HermesServiceManager() {
        serviceMap = new HashMap<>();
    }

    @Override
    public void registerService(String name, Service service) {
        ServiceStore store = serviceMap.computeIfAbsent(name, s -> new HermesServiceStore());

        store.registerService(service);
    }

    @Override
    public Optional<Service> getService(String name) {

        Optional<ServiceStore> optStore = Optional.ofNullable(serviceMap.get(name));

        if (optStore.isPresent()) {
            ServiceStore store = optStore.get();

            return store.getService();
        }

        return Optional.empty();
    }
}
