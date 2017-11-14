package com.example.githubservice.integration;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SuppressWarnings("serial")
@JsonDeserialize(using = GithubEventDeserializer.class)
public class GithubEvent implements Serializable {

	private Long id;

	private String type;

	private String repositoryName;

	private String repositoryUrl;

	private String action;

	private java.time.Instant createdAt;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public String getRepositoryUrl() {
		return repositoryUrl;
	}

	public void setRepositoryUrl(String repositoryUrl) {
		this.repositoryUrl = repositoryUrl;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

}
