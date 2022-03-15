package com.gugucoding.club.security.service;

import com.gugucoding.club.entity.ClubMember;
import com.gugucoding.club.repository.ClubMemberRepository;
import com.gugucoding.club.security.dto.ClubAuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService {

    private final ClubMemberRepository clubMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("ClubUserDetailsService loadUserByUsername "+username);

        Optional<ClubMember> result = clubMemberRepository.findByEmail(username, false);

        if(!result.isPresent()) {
            throw new UsernameNotFoundException("Check Email or Social");
        }

        ClubMember clubMember = result.get();

        log.info("----------------");
        log.info(clubMember);

        ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.isFromSocial(),
                clubMember.getRoleSet().stream()
                        .map(clubMemberRole -> new SimpleGrantedAuthority("ROLE_"+clubMemberRole.toString())).collect(Collectors.toList())
        );
        clubAuthMember.setName(clubAuthMember.getName());
        clubAuthMember.setFromSocial(clubAuthMember.isFromSocial());

        return clubAuthMember;
    }
}
