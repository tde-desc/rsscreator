package de.dsms.rsscreator.application.controller.rest;

import de.dsms.rsscreator.application.model.FeedConfig;
import de.dsms.rsscreator.application.service.FeedConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/feed/config")
public class FeedConfigController {

    private final FeedConfigService feedConfigService;

    @GetMapping
    public List<FeedConfig> getAll() {
        return feedConfigService.getAll();
    }

    @PostMapping
    public FeedConfig post(@RequestBody FeedConfig feedConfig) {
        return feedConfigService.add(feedConfig);
    }

    @PutMapping
    public FeedConfig put(@RequestBody FeedConfig feedConfig) {
        return feedConfigService.update(feedConfig);
    }
}
