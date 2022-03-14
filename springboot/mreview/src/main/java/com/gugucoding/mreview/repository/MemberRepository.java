package com.gugucoding.mreview.repository;

import com.gugucoding.mreview.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
