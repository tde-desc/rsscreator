package de.dsms.rsscreator.application.feed.controller.rest;


import de.dsms.rsscreator.application.feed.service.HttpRequester;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/embeddedpage")
public class EmbeddedPageRestController {

    private final HttpRequester httpRequester;

    @GetMapping
    public String get(String url) {
        return httpRequester.request(url)
                .orElse("The URL could not be called");
    }
}
