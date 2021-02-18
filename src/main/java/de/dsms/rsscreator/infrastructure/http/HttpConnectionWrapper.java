package de.dsms.rsscreator.infrastructure.http;

import de.dsms.rsscreator.application.feed.service.HttpRequester;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class HttpConnectionWrapper implements HttpRequester {
    @Override
    public Optional<String> request(String urlString) {
        try {
            Document doc = Jsoup.connect(urlString).get();
            return Optional.of(doc.outerHtml());
        } catch (IOException e) {
            log.error("URL could not be evaluated", e);
        }
        return Optional.empty();
    }
}
