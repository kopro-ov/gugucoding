package com.gugucoding.board.service;

import com.gugucoding.board.dto.BoardDTO;
import com.gugucoding.board.entity.Board;
import com.gugucoding.board.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

}
