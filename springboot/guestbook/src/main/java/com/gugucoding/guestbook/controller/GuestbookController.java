package com.gugucoding.guestbook.controller;

import com.gugucoding.guestbook.dto.GuestbookDTO;
import com.gugucoding.guestbook.dto.PageRequestDTO;
import com.gugucoding.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService service;

    @GetMapping("/")
    public String index() {

        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list............." + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));

    }

    @GetMapping("/register")
    public void register() {
        log.info("register....");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..."+dto);

        //새로 추가된 엔티티의 번호
        Long gno = service.register(dto);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";

    }

    @GetMapping({"/read", "/modify"})
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("gno" + gno);

        GuestbookDTO dto = service.read(gno);

        model.addAttribute("dto", dto);

    }

    @PostMapping("/modify")
    public String modify(GuestbookDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes) {
        log.info("post modify..");
        log.info("dto" + dto);

        service.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("gno", dto.getGno());


        return "redirect:/guestbook/read";

    }

    @PostMapping("/remove")
    public String remove(Long gno, RedirectAttributes redirectAttributes) {

        log.info("gno remove..."+gno);

        service.remove(gno);

        redirectAttributes.addFlashAttribute("msg", gno);

        return "redirect:/guestbook/list";

    }

}
