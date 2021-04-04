package de.dsms.rsscreator.application.feed.controller.rest;

import de.dsms.rsscreator.domain.feed.entity.FeedConfig;
import de.dsms.rsscreator.domain.feed.event.FeedConfigCreatedEvent;
import de.dsms.rsscreator.domain.feed.repository.FeedConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/feed/config")
public class FeedConfigRestController {

    private final FeedConfigRepository feedConfigRepository;
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping
    public Iterable<FeedConfig> getAll() {
        return feedConfigRepository.findAll();
    }

    @PostMapping
    public FeedConfig post(@RequestBody FeedConfig feedConfig) {
        FeedConfig config = feedConfigRepository.save(feedConfig);
        eventPublisher.publishEvent(new FeedConfigCreatedEvent(config));
        return config;
    }

    @PutMapping
    public FeedConfig put(@RequestBody FeedConfig feedConfig) {
        if (!feedConfigRepository.existsById(feedConfig.getId())) {
            throw new IllegalArgumentException("Die Id " + feedConfig.getId() + " ist unbekannt.");
        }
        return feedConfigRepository.save(feedConfig);
    }
}
