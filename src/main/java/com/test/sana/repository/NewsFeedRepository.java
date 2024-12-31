package com.test.sana.repository;

import com.test.sana.model.NewsFeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long> {
}
