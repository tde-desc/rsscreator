package de.dsms.rsscreator.infrastructure.http;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import de.dsms.rsscreator.application.feed.service.HttpRequester;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class HttpConnectionWrapper implements HttpRequester {
    @Override
    public Optional<String> request(String urlString) {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(true);
        client.getOptions().setJavaScriptEnabled(false);
        try {
            HtmlPage page = client.getPage(urlString);
            return Optional.of(page.getWebResponse().getContentAsString());
        } catch (IOException e) {
            log.error("URL could not be evaluated", e);
        }
        return Optional.empty();
    }
}
