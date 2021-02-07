package com.example.springdatajpa.controller;


import com.example.springdatajpa.domain.Member;
import com.example.springdatajpa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemeberController {

    private final MemberService memberService;


    @Autowired
    public MemeberController(MemberService memberService) {
        this.memberService = memberService;



    }
    @GetMapping("/members")
    public String list(Model model){

        List<Member> list = memberService.findMembers();

        model.addAttribute("members", list);

        return "/members/memberList";

    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createForm(MemberForm memberForm){

        Member member = new Member();
        member.setName(memberForm.getName());
        memberService.join(member);

        return "redirect:/";
    }
}
