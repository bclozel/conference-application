package com.example.speakerservice.github;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubProfileService {

	private final RestTemplate restTemplate;

	private final LoadBalancerClient loadBalancer;

	private final Logger logger = LoggerFactory.getLogger(GithubProfileService.class);

	public GithubProfileService(RestTemplateBuilder builder, LoadBalancerClient loadBalancer) {
		this.restTemplate = builder.build();
		this.loadBalancer = loadBalancer;
	}

	public GithubProfile fetchProfile(String githubId) {
		ServiceInstance serviceInstance = this.loadBalancer.choose("github-service");
		String serviceUrl = serviceInstance.getUri().toString();
		logger.info("Using github-service instance {}", serviceUrl);
		return this.restTemplate
				.getForObject(serviceUrl + "/profiles/{githubId}", GithubProfile.class, githubId);
	}
}
