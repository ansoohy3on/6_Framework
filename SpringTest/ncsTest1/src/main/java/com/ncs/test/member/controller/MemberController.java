package com.ncs.test.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ncs.test.member.model.service.MemberService;
import com.ncs.test.member.model.vo.Member;

@SessionAttributes("loginMember")
@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@RequestMapping("/")
	public String main() {
		return "index";
	}
	
	@PostMapping("login")
	public String memberLogin(Member member, Model model) {
		
		Member loginMember = service.loginMember(member);

		String path = null;
		
		if(loginMember != null) {
			
			model.addAttribute("loginMember", loginMember);
			path = "redirect:/";
			
		} else {
			
			model.addAttribute("msg", "로그인 실패");
			path = "common/errorPage";
			
		}
		
		return path;
	}
}
