package com.example.githubservice.integration;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubClient {

	private final RestTemplate restTemplate;

	public GithubClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	public List<GithubEvent> getRecentEvents(String username) {
		ResponseEntity<GithubEvent[]> response = this.restTemplate
				.getForEntity("https://api.github.com/users/{username}/events", GithubEvent[].class, username);
		return Arrays.asList(response.getBody());
	}

	public GithubUser getUser(String username) {
		return this.restTemplate
				.getForObject("https://api.github.com/users/{username}", GithubUser.class, username);
	}

}