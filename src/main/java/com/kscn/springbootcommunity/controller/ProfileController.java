package com.kscn.springbootcommunity.controller;

import com.kscn.springbootcommunity.dto.PaginationDTO;
import com.kscn.springbootcommunity.model.User;
import com.kscn.springbootcommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    private String profile(HttpServletRequest httpServletRequest, @PathVariable(name = "action") String action, Model model,
                           @RequestParam(name = "page", defaultValue = "1") Integer page
            , @RequestParam(name = "size", defaultValue = "5") Integer size) {


        User user =  (User) httpServletRequest.getSession().getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");

        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");

        }

        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
