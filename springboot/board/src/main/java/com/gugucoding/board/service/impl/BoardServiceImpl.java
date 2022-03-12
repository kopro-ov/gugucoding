package com.gugucoding.board.service.impl;

import com.gugucoding.board.dto.BoardDTO;
import com.gugucoding.board.entity.Board;
import com.gugucoding.board.repository.BoardRepository;
import com.gugucoding.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO dto) {

        log.info(dto);
        Board board = dtoToEntity(dto);
        boardRepository.save(board);

        return board.getBno();
    }

}
