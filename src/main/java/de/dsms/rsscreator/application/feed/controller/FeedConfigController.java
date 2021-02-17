package de.dsms.rsscreator.application.feed.controller;

import de.dsms.rsscreator.domain.feed.entity.FeedConfig;
import de.dsms.rsscreator.domain.feed.repository.FeedConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/feed/config")
public class FeedConfigController {

    private final FeedConfigRepository feedConfigRepository;

    @GetMapping
    public Iterable<FeedConfig> getAll() {
        return feedConfigRepository.findAll();
    }

    @PostMapping
    public FeedConfig post(@RequestBody FeedConfig feedConfig) {
        return feedConfigRepository.save(feedConfig);
    }

    @PutMapping
    public FeedConfig put(@RequestBody FeedConfig feedConfig) {
        if (!feedConfigRepository.existsById(feedConfig.getId())) {
            throw new IllegalArgumentException("Die Id " + feedConfig.getId() + " ist unbekannt.");
        }
        return feedConfigRepository.save(feedConfig);
    }
}
