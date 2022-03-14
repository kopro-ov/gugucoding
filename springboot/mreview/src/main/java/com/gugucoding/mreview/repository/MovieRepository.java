package com.gugucoding.mreview.repository;

import com.gugucoding.mreview.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
