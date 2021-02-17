package de.dsms.rsscreator.application.repository;

import de.dsms.rsscreator.application.model.Feed;
import org.springframework.data.repository.CrudRepository;

public interface FeedRepository extends CrudRepository<Feed, String> {
}
