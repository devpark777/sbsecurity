package com.example.chap5.controller;

import com.example.chap5.entity.Member;
import com.example.chap5.service.ArticleService;
import com.example.chap5.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final ArticleService articleService;

    @GetMapping("/register")
    public String registerForm(Model model) {
//        model.addAttribute("member", new Member());
        return "member/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Member member) {
        memberService.register(member);
        return "redirect:/login";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "member/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("member", memberService.findById(id));
        return "member/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Member member) {
        memberService.update(id, member);
        return "redirect:/members";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        Member member = memberService.findById(id);

        articleService.deleteByWriter(member);
        memberService.delete(id);

        return "redirect:/members";
    }
}
