package nl.tehdreamteam.hermes.main;

import nl.tehdreamteam.hermes.core.HermesServer;
import nl.tehdreamteam.hermes.core.grizzly.GrizzlyHermesServer;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String... args) throws IOException {
        HermesServer server = new GrizzlyHermesServer(1201);

        server.addHandler(new HttpHandler() {
            @Override
            public void service(Request request, Response response) throws Exception {
                final SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
                final String date = format.format(new Date(System.currentTimeMillis()));
                response.setContentType("text/plain");
                response.setContentLength(date.length());
                response.getWriter().write(date);
            }
        });


        server.start();

        System.in.read();
        server.stop();
    }
}
