//package com.google.oauth.token.controller;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Arrays;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.view.RedirectView;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//public class TokenController {
//
//	public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
//	// public static final JsonFactory JSON_FACTORY = new JacksonFactory();
//
////	@GetMapping("/token")
////	public ResponseEntity<?> getGoogleToken() {
////		GoogleTokenResponse response = new GoogleAuthorizationCodeTokenRequest(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY,
////				"clientID", "clientSecret", "authorizationCode", "").execute();
////
////		GoogleCredential refreshTokenCredential = new GoogleCredential.Builder().setJsonFactory(Auth.JSON_FACTORY)
////				.setTransport(Auth.HTTP_TRANSPORT).setClientSecrets("clientId", "clientSecret").build()
////				.setRefreshToken(response.getRefreshToken());
////		refreshTokenCredential.refreshToken();
////		String newAccessToken = refreshTokenCredential.getAccessToken();
////
////		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("Token", newAccessToken));
////
////	}
//
//	@GetMapping(path = "/google")
//	public RedirectView googleLogin() {
//		RedirectView redirectView = new RedirectView();
//		String url = googleLoginGet();
//		log.info("-----URL : {}", url);
//		redirectView.setUrl(url);
//		return redirectView;
//	}
//
//	@GetMapping(path = "/token/login/oauth2/code/google")
//	@ResponseBody
//	public ResponseEntity<?> getGoolgeAuthCodeAndToken(@RequestParam("code") String code, ModelMap model,
//			HttpServletRequest request) throws ServletException, IOException, UnirestException {
//
//		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(),
//				new JacksonFactory(), "792363903912-p85koh3m38l8oh7k8uvv0krva450kt0m.apps.googleusercontent.com",
//				"BFsh4h-JRL_9M4CJM5QQZj5f",
//				Arrays.asList("https://www.googleapis.com/auth/classroom.announcements",
//						"https://www.googleapis.com/auth/classroom.announcements.readonly",
//						"https://www.googleapis.com/auth/classroom.courses",
//						"https://www.googleapis.com/auth/classroom.courses.readonly",
//						"https://www.googleapis.com/auth/classroom.coursework.me",
//						"https://www.googleapis.com/auth/classroom.coursework.me.readonly",
//						"https://www.googleapis.com/auth/classroom.coursework.students",
//						"https://www.googleapis.com/auth/classroom.coursework.students.readonly",
//						"https://www.googleapis.com/auth/classroom.courseworkmaterials",
//						"https://www.googleapis.com/auth/classroom.courseworkmaterials.readonly",
//						"https://www.googleapis.com/auth/classroom.guardianlinks.me.readonly",
//						"https://www.googleapis.com/auth/classroom.guardianlinks.students",
//						"https://www.googleapis.com/auth/classroom.guardianlinks.students.readonly",
//						"https://www.googleapis.com/auth/classroom.profile.emails",
//						"https://www.googleapis.com/auth/classroom.profile.photos",
//						"https://www.googleapis.com/auth/classroom.push-notifications",
//						"https://www.googleapis.com/auth/classroom.rosters",
//						"https://www.googleapis.com/auth/classroom.rosters.readonly",
//						"https://www.googleapis.com/auth/classroom.student-submissions.me.readonly",
//						"https://www.googleapis.com/auth/classroom.student-submissions.students.readonly",
//						"https://www.googleapis.com/auth/classroom.topics",
//						"https://www.googleapis.com/auth/classroom.topics.readonly")).setAccessType("offline").build();
//
//		GoogleTokenResponse authorizationCode = flow.newTokenRequest(code).setGrantType("authorization_code")
//				.setRedirectUri("http://localhost:8080/token/login/oauth2/code/google").execute();
//		String token = authorizationCode.getAccessToken();
//
//		System.out.println(authorizationCode);
//
//		log.info("Token : {}", token);
//
//		HttpResponse<String> response = Unirest.get("https://classroom.googleapis.com/v1/courses")
//				.header("Authorization", "Bearer " + token).asString();
//		System.out.println(response.getStatus());
//
//		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("google_token", token));
//	}
//
//	public String googleLoginGet() {
//		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(new NetHttpTransport(),
//				new JacksonFactory(), "792363903912-p85koh3m38l8oh7k8uvv0krva450kt0m.apps.googleusercontent.com",
//				"BFsh4h-JRL_9M4CJM5QQZj5f",
//				Arrays.asList("https://www.googleapis.com/auth/classroom.announcements",
//						"https://www.googleapis.com/auth/classroom.announcements.readonly",
//						"https://www.googleapis.com/auth/classroom.courses",
//						"https://www.googleapis.com/auth/classroom.courses.readonly",
//						"https://www.googleapis.com/auth/classroom.coursework.me",
//						"https://www.googleapis.com/auth/classroom.coursework.me.readonly",
//						"https://www.googleapis.com/auth/classroom.coursework.students",
//						"https://www.googleapis.com/auth/classroom.coursework.students.readonly",
//						"https://www.googleapis.com/auth/classroom.courseworkmaterials",
//						"https://www.googleapis.com/auth/classroom.courseworkmaterials.readonly",
//						"https://www.googleapis.com/auth/classroom.guardianlinks.me.readonly",
//						"https://www.googleapis.com/auth/classroom.guardianlinks.students",
//						"https://www.googleapis.com/auth/classroom.guardianlinks.students.readonly",
//						"https://www.googleapis.com/auth/classroom.profile.emails",
//						"https://www.googleapis.com/auth/classroom.profile.photos",
//						"https://www.googleapis.com/auth/classroom.push-notifications",
//						"https://www.googleapis.com/auth/classroom.rosters",
//						"https://www.googleapis.com/auth/classroom.rosters.readonly",
//						"https://www.googleapis.com/auth/classroom.student-submissions.me.readonly",
//						"https://www.googleapis.com/auth/classroom.student-submissions.students.readonly",
//						"https://www.googleapis.com/auth/classroom.topics",
//						"https://www.googleapis.com/auth/classroom.topics.readonly")).setAccessType("offline").build();
//		return flow.newAuthorizationUrl().setRedirectUri("http://localhost:8080/token/login/oauth2/code/google")
//				.build();
//	}
//
//	@GetMapping("/test")
//	public ResponseEntity<?> getToken() throws FileNotFoundException, IOException, UnirestException {
//
//		InputStream resourceAsStream = TokenController.class
//				.getResourceAsStream("/instant-form-272208-5e47f1adc9c2.json");
//
//		System.out.println(resourceAsStream);
//
//		String tokenValue = GoogleCredentials.fromStream(resourceAsStream)
//				.createScoped(Arrays.asList("https://www.googleapis.com/auth/classroom.announcements",
//						"https://www.googleapis.com/auth/classroom.announcements.readonly",
//						"https://www.googleapis.com/auth/classroom.courses",
//						"https://www.googleapis.com/auth/classroom.courses.readonly",
//						"https://www.googleapis.com/auth/classroom.coursework.me",
//						"https://www.googleapis.com/auth/classroom.coursework.me.readonly",
//						"https://www.googleapis.com/auth/classroom.coursework.students",
//						"https://www.googleapis.com/auth/classroom.coursework.students.readonly",
//						"https://www.googleapis.com/auth/classroom.courseworkmaterials",
//						"https://www.googleapis.com/auth/classroom.courseworkmaterials.readonly",
//						"https://www.googleapis.com/auth/classroom.guardianlinks.me.readonly",
//						"https://www.googleapis.com/auth/classroom.guardianlinks.students",
//						"https://www.googleapis.com/auth/classroom.guardianlinks.students.readonly",
//						"https://www.googleapis.com/auth/classroom.profile.emails",
//						"https://www.googleapis.com/auth/classroom.profile.photos",
//						"https://www.googleapis.com/auth/classroom.push-notifications",
//						"https://www.googleapis.com/auth/classroom.rosters",
//						"https://www.googleapis.com/auth/classroom.rosters.readonly",
//						"https://www.googleapis.com/auth/classroom.student-submissions.me.readonly",
//						"https://www.googleapis.com/auth/classroom.student-submissions.students.readonly",
//						"https://www.googleapis.com/auth/classroom.topics",
//						"https://www.googleapis.com/auth/classroom.topics.readonly",
//
//						"https://www.googleapis.com/auth/calendar", "https://www.googleapis.com/auth/calendar.events",
//						"https://www.googleapis.com/auth/calendar.events.readonly",
//						"https://www.googleapis.com/auth/calendar.readonly",
//						"https://www.googleapis.com/auth/calendar.settings.readonly"
//
//				)).refreshAccessToken().getTokenValue();
//
//		String token = StringUtils.stripEnd(tokenValue, ".");
//
//		System.out.println(token);
//
//		HttpResponse<String> response = Unirest.get("https://classroom.googleapis.com/v1/courses")
//				.header("Authorization", "Bearer " + token).asString();
//		System.out.println(response.getStatus());
//
////		HttpResponse<String> response = Unirest
////				.get("https://www.googleapis.com/calendar/v3/users/me/calendarList/avinash.patel@gaiansolutions.com")
////				.header("Authorization", "Bearer " + token).asString();
////		System.out.println(response.getBody());
//
//		return null;
//	}
//}
