package com.example.chap5.config;

import com.example.chap5.entity.Article;
import com.example.chap5.entity.Member;
import com.example.chap5.respository.ArticleRepository;
import com.example.chap5.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var passwordEncoder = new BCryptPasswordEncoder();
        Member member1 = Member.builder()
                .name("윤서준")
                .email("yunsejun@mail.com")
                .password(passwordEncoder.encode("111"))
                .role("USER")
                .build();
        memberRepository.save(member1);

        Member member2 = Member.builder()
                .name("관리자")
                .email("admin@mail.com")
                .password(passwordEncoder.encode("111"))
                .role("ADMIN")
                .build();
        memberRepository.save(member2);

        Member member3 = Member.builder()
                .name("홍길동")
                .email("hong@mail.com")
                .password(passwordEncoder.encode("111"))
                .role("USER")
                .build();
        memberRepository.save(member3);

        articleRepository.save(Article.builder()
                        .title("test")
                        .content("내용이 여기에")
                        .writer(member1)
                .build());

        articleRepository.save(Article.builder()
                .title("test2")
                .content("내용이 여기에")
                .writer(member2)
                .build());
    }
}
