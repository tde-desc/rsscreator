package de.dsms.rsscreator.application.controller;

import de.dsms.rsscreator.application.model.FeedConfig;
import de.dsms.rsscreator.application.service.FeedConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/feed/config")
public class FeedConfigController {

    private final FeedConfigService feedConfigService;

    @PostMapping
    public FeedConfig post(FeedConfig feedConfig) {
        return feedConfigService.add(feedConfig);
    }
}
