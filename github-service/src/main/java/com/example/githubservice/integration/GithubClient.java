package com.example.githubservice.integration;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubClient {

	private final RestTemplate restTemplate;

	private final Logger logger = LoggerFactory.getLogger(GithubClient.class);

	public GithubClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}


	public List<GithubEvent> getRecentEvents(String username) {
		ResponseEntity<GithubEvent[]> response = this.restTemplate
				.getForEntity("https://api.github.com/users/{username}/events", GithubEvent[].class, username);

		logger.info("X-RateLimit-Remaining: " + response.getHeaders().get("X-RateLimit-Remaining"));
		return Arrays.asList(response.getBody());
	}

	public GithubUser getUser(String username) {
		ResponseEntity<GithubUser> response = this.restTemplate
				.getForEntity("https://api.github.com/users/{username}", GithubUser.class, username);

		logger.info("X-RateLimit-Remaining: " + response.getHeaders().get("X-RateLimit-Remaining"));
		return response.getBody();
	}

}