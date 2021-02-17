package de.dsms.rsscreator.application.feed.controller;

import de.dsms.rsscreator.application.feed.service.FeedRefresher;
import de.dsms.rsscreator.domain.feed.entity.Feed;
import de.dsms.rsscreator.domain.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rest/feed")
public class FeedController {

    private final FeedRepository feedRepository;
    private final FeedRefresher feedRefresher;

    @GetMapping
    public String getAll() {
        return StreamSupport.stream(feedRepository.findAll().spliterator(), false).map(Feed::getRss).collect(Collectors.joining());
    }

    @GetMapping("/{id}")
    public String get(@PathVariable String id) {
        return feedRepository.findById(id).get().getRss();
    }

    @PutMapping
    public void put() {
        feedRefresher.request();
    }
}
