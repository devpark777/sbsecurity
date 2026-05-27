package com.example.chap5.service;

import com.example.chap5.entity.Member;
import com.example.chap5.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member register(Member member) {

        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new IllegalStateException("This email is already in use.");
        }

        member.setPassword(passwordEncoder.encode(member.getPassword()));

        if (member.getRole() == null || member.getRole().isBlank()) {
            member.setRole("USER");
        }

        return memberRepository.save(member);
    }

//    public Member login(String email, String password) {
//        return memberRepository.findByEmailAndPassword(email, password)
//                .orElse(null);
//    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }

    public void update(Long id, Member formMember) {
        Member member = findById(id);
        member.setName(formMember.getName());
        member.setEmail(formMember.getEmail());

        if (formMember.getPassword() != null && !formMember.getPassword().isBlank()) {
            member.setPassword(passwordEncoder.encode(formMember.getPassword()));
        }
//        member.setPassword(formMember.getPassword());

        member.setRole(formMember.getRole());

        memberRepository.save(member);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }


}