package com.gugucoding.club.controller;

import com.gugucoding.club.security.dto.ClubAuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {

    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public void exAll() {
        log.info("exAll....");
    }

    @PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
    @GetMapping("/member")
    public void member(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember) {
        log.info("member....");

        log.info("-----------------");
        log.info(clubAuthMember);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public void admin() {
        log.info("admin....");
    }

    @PreAuthorize("#clubAuthMember != null && #clubAuthMember.username eq \"user11@aaa.com\"")
    @GetMapping("/exOnly")
    public String exMemberOnly(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember) {

        log.info("exMemberOnly..");
        log.info(clubAuthMember);

        return "/sample/admin";
    }

}
