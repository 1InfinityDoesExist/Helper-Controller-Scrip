package com.google.oauth.token.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelperController {

	private String baURL = "http://ingress-gateway.gaiansolutions.com/broadcasterappstore-service/{tenantId}/1194/baApp/{baId}";

	@GetMapping
	public ResponseEntity<?> restoreImages(@RequestParam(value = "tenantId") String tenantId,
			@RequestParam(value = "baId") String baId) {

		log.info("-----TenantId : {}, and baId : {}", tenantId, baId);
		Unirest.setTimeouts(0, 0);
		try {
			HttpResponse<String> response = Unirest.get(baURL).routeParam("tenantId", tenantId).routeParam("baId", baId)
					.asString();

			if (response.getStatus() == 200) {
				JSONObject baResponse = (JSONObject) new JSONParser().parse(response.getBody());

				for (Object obj : baResponse.keySet()) {
					String param = (String) obj;
					if ("baAppConfig".equalsIgnoreCase(param)) {
						JSONObject baAppConfig = (JSONObject) baResponse.get(param);
						for (Object baAppConfigObj : baAppConfig.keySet()) {
							String baAppConfigParam = (String) baAppConfigObj;
							if ("files".equalsIgnoreCase(param)) {
								JSONArray filesArray = (JSONArray) baAppConfig.get(baAppConfigParam);
								for (int iter = 0; iter < filesArray.size(); iter++) {
									JSONObject fileObject = (JSONObject) filesArray.get(iter);
									if (ObjectUtils.isEmpty(fileObject.get("content"))) {
										JSONObject contentObject = (JSONObject) fileObject.get("content");
										String html = (String) contentObject.get("pre");

										System.out.println(html);

										break;
									}
								}
							}
						}
					}
				}

			}
		} catch (UnirestException | ParseException e) {
			e.printStackTrace();
		}
		return null;

	}
}
