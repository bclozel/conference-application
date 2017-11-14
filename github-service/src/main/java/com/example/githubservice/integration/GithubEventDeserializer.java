package com.example.githubservice.integration;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.boot.jackson.JsonObjectDeserializer;

public class GithubEventDeserializer extends JsonObjectDeserializer<GithubEvent> {

	@Override
	protected GithubEvent deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext,
			ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {

		GithubEvent event = new GithubEvent();
		event.setId(Long.parseLong(nullSafeValue(jsonNode.get("id"), String.class), 10));
		String type = nullSafeValue(jsonNode.get("type"), String.class);
		event.setType(type);
		JsonNode repoNode = jsonNode.get("repo");
		event.setRepositoryName(nullSafeValue(repoNode.get("name"), String.class));
		event.setRepositoryUrl(nullSafeValue(repoNode.get("url"), String.class)
				.replace("https://api.github.com/repos/", "https://github.com/"));
		event.setCreatedAt(Instant.parse(nullSafeValue(jsonNode.get("created_at"), String.class)));
		event.setAction(deduceAction(type));
		return event;
	}

	private String deduceAction(String type) {

		switch (type) {
			case "CommitCommentEvent":
				return "commented on a commit";
			case "CreateEvent":
				return "created a repository";
			case "DeleteEvent":
				return "deleted a branch";
			case "ForkEvent":
				return "forked a repository";
			case "GollumEvent":
				return "updated a wiki page";
			case "IssueCommentEvent":
				return "commented on an issue";
			case "IssuesEvent":
				return "updated an issue";
			case "LabelEvent":
				return "updated a label";
			case "MilestoneEvent":
				return "updated a milestone";
			case "PullRequestEvent":
				return "updated a pull request";
			case "PullRequestReviewEvent":
				return "reviewed a pull request";
			case "PullRequestReviewCommentEvent":
				return "commented on a pull request review";
			case "PushEvent":
				return "pushed commits to a branch";
		}
		return "performed an action";
	}
}
