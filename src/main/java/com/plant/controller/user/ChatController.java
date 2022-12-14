package com.plant.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

	@GetMapping("/chatting")
	public String regForm() {
		return "chat/chat";
	}
	
	@GetMapping("/chatList")
	public String chatList() {
		return "chat/chatList";
	}
	
	@GetMapping("/chat2")
	public String chat2(String chatNo, Model model) {
		System.out.println("채팅방번호:" + chatNo);
		model.addAttribute("chatNo", chatNo);
		return "chat/chat2";
	}
}
