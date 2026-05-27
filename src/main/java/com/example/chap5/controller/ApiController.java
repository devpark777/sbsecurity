package com.example.chap5.controller;

import com.example.chap5.entity.Article;
import com.example.chap5.entity.Member;
import com.example.chap5.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final MemberService memberService;

    private List<Member> members = List.of(
            new Member(1L, "윤서준", "yun1@mail.com", "111", "USER"),
            new Member(2L, "윤서준2", "yun2@mail.com", "111", "USER"),
            new Member(3L, "윤서준3", "yun3@mail.com", "111", "USER"),
            new Member(4L, "윤서준4", "yun4@mail.com", "111", "USER")
    );

    @GetMapping("/api/members")
    public List<Member> getMembers() {
        return members;
    }

    // React 연결 실습 위함
    @PostMapping("/api/members")
    public ResponseEntity<String> join(@RequestBody Member member) {
        System.out.println(member);
        memberService.register(member);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/api/articles")
    public ResponseEntity<String> write(@RequestBody Article articleDTO) {
        System.out.println(articleDTO);
        return ResponseEntity.ok("글작성 성공");
    }
}
