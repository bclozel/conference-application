package com.example.speakerservice.web;

import com.example.speakerservice.domain.SpeakerRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SpeakerController {

	private final SpeakerRepository speakerRepository;

	public SpeakerController(SpeakerRepository speakerRepository) {
		this.speakerRepository = speakerRepository;
	}

	@GetMapping("/profiles/{name}")
	public String showProfile(@PathVariable String name, Model model) {
		model.addAttribute("speaker", speakerRepository.findByGithub(name));
		return "profile";
	}
}
