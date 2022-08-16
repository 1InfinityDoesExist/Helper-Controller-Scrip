package com.google.oauth.token.request;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class PageCreationRequest {
	private String id;
	private String name;
	private String description;
	private ChannelType channelType;
	private boolean published;
	private List<Object> resolutions;
	private String thumbnail;
	private HashMap<String, Boolean> devicesCompatibility;
	private HashMap<String, Boolean> orientationsCompatibility;
	private List<HashMap<String, String>> configuration;
	private String contentType;
	private Object content;

}