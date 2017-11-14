package com.example.speakerservice.github;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class GithubProfile implements Serializable {

	private GithubUser user;

	private List<GithubEvent> events;

	public GithubProfile() {
	}

	public GithubProfile(GithubUser user, List<GithubEvent> events) {
		this.user = user;
		this.events = events;
	}

	public GithubUser getUser() {
		return user;
	}

	public void setUser(GithubUser user) {
		this.user = user;
	}

	public List<GithubEvent> getEvents() {
		return events;
	}

	public void setEvents(List<GithubEvent> events) {
		this.events = events;
	}
}
