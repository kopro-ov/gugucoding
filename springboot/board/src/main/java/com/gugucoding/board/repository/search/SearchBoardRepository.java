package com.gugucoding.board.repository.search;

import com.gugucoding.board.entity.Board;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface SearchBoardRepository {

    Board search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
