package com.boot.demo.repos;

import com.boot.demo.model.Feed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends CrudRepository<Feed, Long> {
    Feed findFeedByFood(String food);

    Feed findFeedById(Long id);
}
