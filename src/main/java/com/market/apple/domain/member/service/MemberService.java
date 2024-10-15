package com.market.apple.domain.member.service;

import com.market.apple.domain.member.entity.Member;
import com.market.apple.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member getMember(String username) {
        return this.memberRepository.findByUsername(username);
    }

    public Member create (String username, String password, String email) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setEmail(email);
        return this.memberRepository.save(member);
    }
}
