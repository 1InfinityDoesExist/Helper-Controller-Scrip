package com.google.oauth.token.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.configuration.CodecRegistry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/debug")
public class TestController {

	@GetMapping("/product")
	public ResponseEntity<?> figureOutIssue() throws UnirestException, ParseException {

		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.get(
				"http://ingress-gateway.gaiansolutions.com/marketplace-service/api/v1.0/products?pageNo=0&pageSize=500&sortBy=id&sortOrder=ASCENDING")
				.asString();

		JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.getBody());

		JSONArray jsonArray = (JSONArray) jsonObject.get("dtos");

		for (int iter = 0; iter < jsonArray.size(); iter++) {
			log.info("----------------------- : {} : ----------------", iter);
			JSONObject obj = (JSONObject) jsonArray.get(iter);
			for (Object params : obj.keySet()) {
				String param = (String) params;
				if (param.equalsIgnoreCase("activeSince")) {
					ZonedDateTime dateTime1 = Instant.ofEpochMilli(((Double) obj.get(param)).longValue())
							.atZone(ZoneId.of("Asia/Calcutta"));
					log.info("---activeSince  {}", dateTime1);
				}

				if (param.equalsIgnoreCase("createdOn")) {
					ZonedDateTime dateTime2 = Instant.ofEpochMilli(((Double) obj.get(param)).longValue())
							.atZone(ZoneId.of("Asia/Calcutta"));
					log.info("---createdOn : {}", dateTime2);

				}
			}
		}
		return null;

	}

	@GetMapping("/marketplace")
	public ResponseEntity<?> figureOutIssue_2() throws UnirestException, ParseException, InterruptedException {
		List<JSONObject> response = new ArrayList<>();
		String connectionString = "mongodb://localhost:27017";
		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			List<org.bson.Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
			databases.forEach(db -> System.out.println(db.toJson()));

		}

		List<String> working = new ArrayList<>();

		List<String> notWorking = new ArrayList<>();

		int count = 0;
		// String connectionString = "mongodb://localhost:27017/marketplace";
		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			MongoDatabase sampleTrainingDB = mongoClient.getDatabase("marketplace");

			MongoCollection<org.bson.Document> fromCollection = sampleTrainingDB.getCollection("product_08_08_2022");

			// find one document with new Document
//			FindIterable<org.bson.Document> products = gradesCollection.find();

//			for (org.bson.Document doc : products) {
//
////				System.out.println("************** Count**********************" + count++);
////				System.out.println(doc.get("created_on"));
////				System.out.println(doc.get("updated_on"));
////				System.out.println(doc.get("active_since"));
//
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("activeSince", doc.get("active_since"));
//				jsonObject.put("createdOn", doc.get("created_on"));
//				jsonObject.put("updatedOn", doc.get("created_on"));
//				// response.add(jsonObject);
//
//				working.add((String) doc.get("_id"));
//				// System.out.println("************************************");
//			}

			MongoCollection<org.bson.Document> toColleciton = sampleTrainingDB.getCollection("product");

			List<org.bson.Document> batchObject = new ArrayList<>();
			int cnt = 0;
			// find one document with new Document
			FindIterable<org.bson.Document> products1 = fromCollection.find();
			for (org.bson.Document doc : products1) {
				cnt++;

				batchObject.add(doc);
				System.out.println("************** Count**********************" + count++);
//				System.out.println(doc.get("created_on"));
//				System.out.println(doc.get("updated_on"));
//				System.out.println(doc.get("active_since"));

//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("activeSince", doc.get("active_since"));
//				jsonObject.put("createdOn", doc.get("created_on"));
//				jsonObject.put("updatedOn", doc.get("created_on"));
//				jsonObject.put("id", (String) doc.get("_id"));
//				response.add(jsonObject);
//
//				notWorking.add((String) doc.get("_id"));
//				// System.out.println("************************************");

				System.out.println((String) doc.get("_id"));

//				String json = null;
//				try {
//					json = doc.toJson();
//
//					if (json.contains("54578")) {
//						System.out.println("++++++++++++++ddddddddddddddddd+++++++++++++++" + (String) doc.get("_id"));
//					} else {
//						continue;
//					}
//					System.out.println("+++++ssss " + json);
//				} catch (Exception ex) {
//					System.out.println("-------Failed-----------");
//				}

//				JSONObject copy = (JSONObject) new JSONParser().parse(json);
//				System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
////				if (copy.get("createdAt") instanceof String) {
////					System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPP");
////					copy.put("createdAt", (long) copy.get("createdAt"));
////					copy.put("updatedAt", (long) copy.get("createdAt"));
////
////				}

				System.out.println("((((((((((((((((((((((((((((((");

				// org.bson.Document docd = org.bson.Document.parse(json);

				System.out.println("+++++++++++++++++++++++++++++++++++++");

				if (count == 20) {
					toColleciton.insertMany(batchObject);
//					toColleciton.insertOne(doc);

					Thread.sleep(90000);
				}

				System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");

//				if (!working.contains(((String) doc.get("_id")))) {
//					System.out.println("----Inserting into db : " + (String) doc.get("_id"));
//					//toColleciton.insertOne(doc);
//
////					System.out.println("--------Waiting for data to sync with elastic-------");
////
////					if (cnt == 180) {
////						Thread.sleep(60000);
////					} else {
////						Thread.sleep(3000);
////					}
////					System.out.println("---------Woke UP----------------");
//				}

			}

		}

		System.out.println("______________________________________________________________");

		List<String> diff = notWorking.stream().filter(e -> !working.contains(e)).collect(Collectors.toList());

		for (int iter = 0; iter < diff.size(); iter++) {

		}

		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("response", response)
				.addAttribute("working", working).addAttribute("not_working", notWorking).addAttribute("diff", diff));

	}

	@SuppressWarnings("unchecked")
	@GetMapping("/migrate")
	public ResponseEntity<ModelMap> baasToPage() throws UnirestException, ParseException {

		List<String> success = new ArrayList<>();
		List<String> failed = new ArrayList<>();
		System.out.println("--------------Baas TO Page----------------");
		String connectionString = "mongodb://localhost:27017/marketplace";
		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			MongoDatabase baDB = mongoClient.getDatabase("marketplace");

			MongoCollection<org.bson.Document> baAppCollections = baDB.getCollection("product");

			FindIterable<org.bson.Document> baApp = baAppCollections.find();
			for (org.bson.Document doc : baApp) {

				System.out.println("***************************************");

				System.out.println(doc);
//				baAppCollections.deleteOne(doc);
//				if (true) {
//					continue;
//				}

				System.out.println("787777777777777777777777777777777777777777777777777");
				String jsonBaAPP = doc.toString();
				// JSONObject dbBaAsJSON = (JSONObject) new JSONParser().parse(jsonBaAPP);

				System.out.println("22222222222222222222222222222222222222222222222222222222222222");
				// JSONObject jobj = (JSONObject) dbBaAsJSON.get("_id");
				String id = (String) doc.get("_id");

				System.out.println("______________________________________________");
				// (String) jobj.get("$oid");
				// System.out.println(id);

				System.out.println(id);
//				JSONObject copy = dbBaAsJSON;
//				copy.put("id", id);
//				copy.remove("_id");
//				copy.remove("_class");

				System.out.println("++++++++++++++++++++++++++++++++++++++");
				if (jsonBaAPP.contains("54578") && !id.equalsIgnoreCase("628bcaf26362d5000126b0a6")) {
					System.out.println();
					return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("msg", id));
				} else {
					System.out.println("--------Continue----------------");
					continue;
				}

//				System.out.println(":::::::::::::::::::::::::::::");
//				if (copy.get("createdAt") instanceof String) {
//					System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPP");
//					copy.put("createdAt", copy.get("createdAt"));
//					copy.put("updatedAt", copy.get("createdAt"));
//
//				} else {
//					if ((long) copy.get("updatedAt") == 0) {
//						copy.put("updatedAt", copy.get("createdAt"));
//					}
//				}
//
//				System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
//				PageCreationRequest pageCreationRequest = PageCreationRequest.builder().channelType(ChannelType.MEF)
//						.configuration(new ArrayList<HashMap<String, String>>()).content(copy).description("")
//						.devicesCompatibility(new LinkedHashMap<String, Boolean>()).name("")
//						.orientationsCompatibility(new LinkedHashMap<String, Boolean>()).published(true)
//						.resolutions(new ArrayList<Object>()).thumbnail("").id(id).build();
//				for (Object obj : dbBaAsJSON.keySet()) {
//					String param = (String) obj;
////					System.out.println("-------Param : " + param + " Value : " + dbBaAsJSON.get(param));
//					if (param.equalsIgnoreCase("baAppName")) {
//						pageCreationRequest.setName((String) dbBaAsJSON.get(param));
//					}
//
//					if (param.equalsIgnoreCase("published")) {
//						pageCreationRequest.setPublished((boolean) dbBaAsJSON.get(param));
//					}
//
//					if (param.equalsIgnoreCase("baAppConfig")) {
//
//						JSONObject baConfigJSON = (JSONObject) dbBaAsJSON.get(param);
//						for (Object o : baConfigJSON.keySet()) {
//							String oParam = (String) o;
//							System.out.println("-----OParam :" + o);
//							if (oParam.equalsIgnoreCase("thumbnail")) {
//								pageCreationRequest.setThumbnail((String) baConfigJSON.get(oParam));
//							}
//						}
//
//					}
//				}
//
//				System.out.println("--------------------Paylaod Created-----------------------");
////				System.out.println((JSONObject) new JSONParser()
////						.parse(new ObjectMapper().writeValueAsString(pageCreationRequest)));
//
//				try {
//					Unirest.setTimeouts(0, 0);
//					HttpResponse<String> response = Unirest.post(prod).header("Content-Type", "application/json")
//							.header("Authorization",
//									"Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6ImZmOGYxNjhmLTNmZjYtNDZlMi1iMTJlLWE2YTdlN2Y2YTY5MCJ9.eyJzdWIiOiJnYWlhbi5jb20iLCJ1c2VyX25hbWUiOiJwb3J0YWxfdGVzdCIsInNjb3BlIjpbInRydXN0IiwicmVhZCIsIndyaXRlIl0sInRlbmFudElkIjoiNjExYmRkMzQyNmE5NDg2MDA1NjkzYjExIiwiaXNzIjoiZ2FpYW4uY29tIiwidXNlck5hbWUiOiJwb3J0YWxfdGVzdCIsImF1dGhvcml0aWVzIjpbIlJPTEVfT01OSV9DT05TVU1FUiIsIlJPTEVfTUFSS0VUUExBQ0VfVVNFUiIsIlJPTEVfT01OSV9VU0VSIl0sImp0aSI6IjgxODE1ZDNmLTY1MTAtNDJkNC05NWZkLTNiZTJmMWYzYjg5ZiIsImVtYWlsIjoicG9ydGFsX3Rlc3RAZ2F0ZXN0YXV0b21hdGlvbi5jb20iLCJjbGllbnRfaWQiOiJnYWlhbiJ9.Mz1gWLt1rujlQWW3SzuwtERk1i6HwG9utVuMUnL-RX4kKtR1jl0eR9MZmNjRZ0znbrr6w8MOj2aAULtpIEYmM9jU_mXGBuqetPIbTuV2d4Hkv6f0qaJZLAIAU3qhgijQI9O4a2yg_rmHnibNhEcZMKEFK5AXw8M_B8XIgnNYlXDkpjEqP6Siv0HJmHA3T1j1XY8PCsluzIwDzIgRr-xqAJcaCnUwGR7XxsF-X0plk8L9qV1Z3bF2EMqqBsednYeqaM3EqwJXk27R5PFU7jn5aOc-_n9DxaGLcuJB5JoqoGW7DeaIKLzMwxvS9vP_bc8vDOxl8xk-zTRAq8goyHV6IQ")
//							.body(new ObjectMapper().writeValueAsString(pageCreationRequest)).asString();
//
////					System.out.println(response.getBody());
//
//					if (response.getStatus() == 201 || response.getStatus() == 200) {
//						System.out.println("------Success----------");
//						success.add(id);
//						baAppCollections.deleteOne(doc);
//					}
//
//				} catch (Exception ex) {
//					failed.add(id);
//				}
			}

		} catch (Exception e) {
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ModelMap().addAttribute("success", success).addAttribute("success_count", success.size())
						.addAttribute("failed", failed).addAttribute("failed_count", failed.size()));

	}

	/**
	 * Figure out elastic issue
	 * 
	 * @return
	 */
	@GetMapping("/col-to-col")
	public ResponseEntity<?> oneCollectionToAnother() {

		// for DBRef
		CodecRegistry codecRegistry = MongoClientSettings.getDefaultCodecRegistry();
		final DocumentCodec codec = new DocumentCodec(codecRegistry, new BsonTypeClassMap());

		String connectionString = "mongodb://localhost:27017";
		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			MongoDatabase mongoDatabase = mongoClient.getDatabase("marketplace");

			MongoCollection<org.bson.Document> fromDocuments = mongoDatabase.getCollection("product_08_08_2022");
			MongoCollection<org.bson.Document> toDocuments = mongoDatabase.getCollection("product");

			int count = 0;
			FindIterable<org.bson.Document> documentIterator = fromDocuments.find();
			for (org.bson.Document doc : documentIterator) {
				// log.info("-----Document : {}", doc);

				count++;
				String id = doc.getString("_id");
				log.info("-----Document id : {}", id);

				String jsonString = doc.toJson(codec);
				// log.info("-----JSONString value : {}", jsonString);

				
				
				
				if (jsonString.contains("54580")) {
					System.out.println(id);
				}

				org.bson.Document modifiedDoc = org.bson.Document.parse(jsonString);

				toDocuments.insertOne(modifiedDoc);
				Thread.sleep(100);

			}
		} catch (Exception ex) {
			log.info("-----Error : {}", ex.getMessage());
		}

		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("msg", "Success"));

	}

	/**
	 * Difference between db data and elastic data
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/elastic")
	public ResponseEntity<?> getElasticData() {

		List<String> dbIds = new ArrayList<>();

		String connectionString = "mongodb://localhost:27017/marketplace";
		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			MongoDatabase baDB = mongoClient.getDatabase("marketplace");

			MongoCollection<org.bson.Document> baAppCollections = baDB.getCollection("product_08_08_2022");

			FindIterable<org.bson.Document> baApp = baAppCollections.find();
			for (org.bson.Document doc : baApp) {
				dbIds.add((String) doc.get("_id"));

			}
		} catch (Exception e) {
		}

		List<String> ids = new ArrayList<>();

		Unirest.setTimeouts(0, 0);
		try {
			HttpResponse<String> response = Unirest.get(
					"https://ingress-gateway.gaiansolutions.com/marketplace-service/api/v1.0/search?itemType=PRODUCT&name=&page=0&size=2000")
					.asString();

			if (response.getStatus() == 200) {
				JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.getBody());
				for (Object obj : jsonObject.keySet()) {
					String param = (String) obj;
					if ("combinedResult".equalsIgnoreCase(param)) {
						JSONArray jsonArray = (JSONArray) jsonObject.get(param);
						jsonArray.parallelStream().forEach(doc -> {
							ids.add((String) ((JSONObject) (((JSONObject) doc).get("model"))).get("id"));
						});
					}
				}
			}
		} catch (UnirestException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(dbIds.size() + "-> " + ids.size());
		dbIds.removeAll(ids);
		return ResponseEntity.status(HttpStatus.OK).body(new ModelMap().addAttribute("mag", dbIds));

	}

}
