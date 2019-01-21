package de.dsms.rsscreator.application.repository;

import de.dsms.rsscreator.application.model.Feed;
import de.dsms.rsscreator.application.model.FeedConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedRepository extends MongoRepository<Feed, String> {
}
