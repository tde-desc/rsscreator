package de.dsms.rsscreator.application.controller.rest;

import de.dsms.rsscreator.application.model.Feed;
import de.dsms.rsscreator.application.repository.FeedRepository;
import de.dsms.rsscreator.application.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/feed")
public class FeedController {

    private final FeedRepository feedRepository;
    private final FeedService feedService;

    @GetMapping
    public String getAll() {
        return feedRepository.findAll().stream().map(Feed::getRss).collect(Collectors.joining());
    }

    @GetMapping("/{id}")
    public String get(@PathVariable String id) {
        return feedRepository.findById(id).get().getRss();
    }

    @PutMapping
    public void put() {
        feedService.schedule();
    }
}
