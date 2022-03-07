package com.gugucoding.springboot.repository;

import com.gugucoding.springboot.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
