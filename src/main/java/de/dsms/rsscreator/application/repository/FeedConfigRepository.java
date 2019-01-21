package de.dsms.rsscreator.application.repository;

import de.dsms.rsscreator.application.model.FeedConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedConfigRepository extends MongoRepository<FeedConfig, String> {
}
