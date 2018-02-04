package nl.tehdreamteam.hermes.server.core;

public interface ServiceManager extends ServiceLocator {

    void registerService(String name, Service service);
}
