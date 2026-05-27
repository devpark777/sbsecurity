package com.example.chap5.controller;

import com.example.chap5.entity.Article;
import com.example.chap5.entity.Member;
import com.example.chap5.service.ArticleService;
import com.example.chap5.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("articles", articleService.findAll());
        return "article/list";
    }

    @GetMapping("/write")
    public String writeForm(HttpSession session, Model model) {
        model.addAttribute("article", new Article());
        return "article/write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute Article article, Authentication authentication) {

        String email = authentication.getName();
        Member member = memberService.findByEmail(email);

        articleService.create(article, member);
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "article/detail";
    }

    @GetMapping("/my")
    public String myArticles(Authentication authentication, Model model) {

        String email = authentication.getName();
        Member loginMember = memberService.findByEmail(email);

        model.addAttribute("articles", articleService.findMyArticles(loginMember));
        return "article/my";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Authentication authentication, Model model) {

        Article article = articleService.findById(id);

        String email = authentication.getName();
        Member loginMember = memberService.findByEmail(email);

        boolean isWriter = article.getWriter().getId().equals(loginMember.getId());
        boolean isAdmin = "ADMIN".equals(loginMember.getRole());

        if (!isWriter && !isAdmin) { // 글작성자, 또는 관리자 아니면
            return "redirect:/articles";
        }

        model.addAttribute("article", article);
        return "article/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @ModelAttribute Article article,  Authentication authentication) {

        Article originalArticle = articleService.findById(id);

        String email = authentication.getName();
        Member loginMember = memberService.findByEmail(email);

        boolean isWriter = originalArticle.getWriter().getId().equals(loginMember.getId());
        boolean isAdmin = "ADMIN".equals(loginMember.getRole());

        if (!isWriter && !isAdmin) {
            return "redirect:/articles";
        }

        articleService.update(id, article);
        return "redirect:/articles/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Authentication authentication) {

        Article article = articleService.findById(id);
        String email = authentication.getName();
        Member loginMember = memberService.findByEmail(email);

        boolean isWriter = article.getWriter().getId().equals(loginMember.getId());
        boolean isAdmin = "ADMIN".equals(loginMember.getRole());

        if (!isWriter && !isAdmin) {
            return "redirect:/articles";
        }

        articleService.delete(id);
        return "redirect:/articles";
    }
}
