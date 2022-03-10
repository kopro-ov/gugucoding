package com.gugucoding.guestbook.service.impl;

import com.gugucoding.guestbook.dto.GuestbookDTO;
import com.gugucoding.guestbook.entity.Guestbook;
import com.gugucoding.guestbook.repository.GuestbookRepository;
import com.gugucoding.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestbookDTO guestbookDTO) {

        log.info("DTO ---------------");
        log.info(guestbookDTO);

        Guestbook entity = dtoToEntity(guestbookDTO);
        log.info(entity);

        guestbookRepository.save(entity);

        return entity.getGno();
    }

}
