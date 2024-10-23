package com.market.apple.domain.levelup.controller;

import com.market.apple.domain.article.entity.Article;
import com.market.apple.domain.levelup.entity.Levelup;
import com.market.apple.domain.levelup.form.LevelupForm;
import com.market.apple.domain.levelup.service.LevelupService;
import com.market.apple.domain.member.entity.Member;
import com.market.apple.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class LevelupController {
    private final LevelupService levelupService ;
    private final MemberService memberService;
    @GetMapping("/levelup/list")
    public String list(Model model) {
        List<Levelup> levelupList = this.levelupService.getList();
        model.addAttribute("levelupList", levelupList);
        return "levelup/list";
    }

    @GetMapping("levelup/detail/{id}")
    public String getLevelup (Model model, @PathVariable("id") Long id) {
        Levelup levelup = this.levelupService.getLevelup(id);
        model.addAttribute("levelup", levelup);
        return "levelup/detail";
    }
    @GetMapping("levelup/create")
    public String create(LevelupForm levelupForm) {
        return "levelup/form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("levelup/create")
    public String create(@Valid LevelupForm levelupForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "levelup/form/";
        }
        Member member = this.memberService.getMember(principal.getName());

        this.levelupService.create(levelupForm.getTitle(), levelupForm.getContent(), false, member);
        return "redirect:/levelup/list";
    }
}
