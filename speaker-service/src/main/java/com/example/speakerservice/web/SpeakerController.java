package com.example.speakerservice.web;

import com.example.speakerservice.domain.SpeakerRepository;
import com.example.speakerservice.github.GithubProfileService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SpeakerController {

	private final SpeakerRepository speakerRepository;

	private final GithubProfileService githubProfileService;

	public SpeakerController(SpeakerRepository speakerRepository, GithubProfileService githubProfileService) {
		this.speakerRepository = speakerRepository;
		this.githubProfileService = githubProfileService;
	}

	@GetMapping("/profiles/{name}")
	public String showProfile(@PathVariable String name, Model model) {
		model.addAttribute("speaker", speakerRepository.findByGithub(name));
		model.addAttribute("github", githubProfileService.fetchProfile(name));
		return "profile";
	}
}
