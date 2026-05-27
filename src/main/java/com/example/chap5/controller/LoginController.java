package com.example.chap5.controller;

import com.example.chap5.entity.Member;
import com.example.chap5.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String email,
//                        @RequestParam String password,
//                        HttpSession session) {
//
//        Member loginMember = memberService.login(email, password);
//
//        if (loginMember == null) {
//            return "redirect:/login?error=true";
//        }
//
//        session.setAttribute("loginMember", loginMember);
//        return "redirect:/";
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
