package de.dsms.rsscreator.domain.feed.repository;

import de.dsms.rsscreator.domain.feed.entity.Feed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends CrudRepository<Feed, String> {
}
