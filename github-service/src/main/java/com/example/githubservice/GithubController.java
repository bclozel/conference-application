package com.example.githubservice;

import java.util.List;

import com.example.githubservice.integration.GithubClient;
import com.example.githubservice.integration.GithubEvent;
import com.example.githubservice.integration.GithubProfile;
import com.example.githubservice.integration.GithubUser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubController {

	private final GithubClient client;

	public GithubController(GithubClient client) {
		this.client = client;
	}

	@GetMapping("/profiles/{username}")
	public GithubProfile fetchGithubProfile(@PathVariable String username) throws InterruptedException {
		// evil latency!
		if ("jhoeller".equals(username)) {
			Thread.sleep(2000);
		}
		GithubUser user = client.getUser(username);
		List<GithubEvent> events = client.getRecentEvents(username);

		return new GithubProfile(user, events);
	}
}
