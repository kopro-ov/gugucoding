package com.gugucoding.mreview.repository;

import com.gugucoding.mreview.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
