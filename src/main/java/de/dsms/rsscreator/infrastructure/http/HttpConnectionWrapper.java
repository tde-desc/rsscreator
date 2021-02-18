package de.dsms.rsscreator.infrastructure.http;

import de.dsms.rsscreator.application.feed.service.HttpRequester;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

@Slf4j
@Component
public class HttpConnectionWrapper implements HttpRequester {
    @Override
    public Optional<String> request(String urlString) {
        try {
            final URL url = new URL(urlString);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                return Optional.of(content.toString());
            }
        } catch (MalformedURLException e) {
            log.error("URL is malformed", e);
        } catch (IOException e) {
            log.error("Error performing request", e);
        }
        return Optional.empty();
    }
}
