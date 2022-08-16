package com.google.oauth.token;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@SpringBootApplication
public class TokenApplication {

	public static void main(String[] args) throws UnirestException, NoSuchAlgorithmException {
		SpringApplication.run(TokenApplication.class, args);

		//pay();
	}

	public static void pay() throws UnirestException, NoSuchAlgorithmException {

		String apiEndPoint = "/v3/transaction/sdk-less/initiate";

		// "/v4/debit";

		String salt = "099eb0cd-02cf-4e2a-8aca-3e6c6aff0399";

		HashMap<String, Object> data = new HashMap<>();
		data.put("merchantId", "PGTESTPAYUAT"); // String. Mandatory
		data.put("transactionId", "TX123456789"); // String. Mandatory.
		data.put("amount", 100); // Long. Mandatory
		data.put("validFor", 900000);
		data.put("merchantOrderId", "OD1234"); // String. Mandatory
		data.put("redirectUrl", "https://test-merchant.com/order/42314233232");
		data.put("transactionContext",
				"ewoJIm9yZGVyQ29udGV4dCI6IHsKCQkidHJhY2tpbmdJbmZvIjogewoJCQkidHlwZSI6ICJIVFRQUyIsCgkJCSJ1cmwiOiAiaHR0cHM6Ly9nb29nbGUuY29tIgoJCX0KCX0sCgkiZmFyZURldGFpbHMiOiB7CgkJInRvdGFsQW1vdW50IjogMzkwMCwKCQkicGF5YWJsZUFtb3VudCI6IDM5MDAKCX0sCgkiY2FydERldGFpbHMiOiB7CgkJImNhcnRJdGVtcyI6IFt7CgkJCSJjYXRlZ29yeSI6ICJTSE9QUElORyIsCgkJCSJpdGVtSWQiOiAiMTIzNDU2Nzg5MCIsCgkJCSJwcmljZSI6IDM5MDAsCgkJCSJpdGVtTmFtZSI6ICJURVNUIiwKCQkJInF1YW50aXR5IjogMQoJCX1dCgl9Cn0=");

		String base64Body = Base64.getEncoder().encodeToString((new Gson().toJson(data)).getBytes());

		String sha256hex = DigestUtils.sha256Hex(base64Body + apiEndPoint + salt) + "###" + 1;

		System.out.println(sha256hex);

		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response;
		try {
			response = Unirest.post("https://apps-uat.phonepe.com/v3/transaction/sdk-less/initiate")
					.header("X-VERIFY", sha256hex).header("X-CLIENT-ID", "PGTESTPAYUAT")
					.header("X-CALLBACK-URL", "https://mykewlapp.com/callback").header("Accept", "application/json")
					.header("Content-Type", "application/json").asString();
			System.out.println(response.getStatus());
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
