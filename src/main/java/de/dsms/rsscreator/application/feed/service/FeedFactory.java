package de.dsms.rsscreator.application.feed.service;

import de.dsms.rsscreator.domain.feed.entity.FeedConfig;

import java.util.Optional;

public interface FeedFactory {
    Optional<String> createFeed(FeedConfig feedConfig);
}
