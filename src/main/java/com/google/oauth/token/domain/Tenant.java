//package com.google.oauth.token.domain;
//
//import java.io.Serializable;
//import java.time.Instant;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.geojson.FeatureCollection;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//
//import com.gaian.marketplace.domain.Currency;
//import com.gaian.marketplace.enums.TenantType;
//import com.gaian.marketplace.service.request.Address;
//
//import lombok.Data;
//
///**
// * A Tenant.
// */
//
//@Data
//@Document(collection = "tenant")
//public class Tenant implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Field("id")
//	private String id;
//
//	@Field("name")
//	private String name;
//
//	@Field("countryCode")
//	private String countryCode;
//
//	@Field("description")
//	private String description;
//
//	@Field("version")
//	private String version;
//
//	@Field("url")
//	private String url;
//
//	@Field("createdBy")
//	private String createdBy;
//
//	@Field("email_id")
//	private String emailId;
//
//	@Field("organization_name")
//	private String organizationName;
//
//	@Field("registration_number")
//	private String registrationNumber;
//
//	@Field("website")
//	private String website;
//
//	@Field("created_on")
//	private Instant createdOn;
//
//	@Field("up_instant_on")
//	private Instant upInstantOn;
//
//	@Field("up_instant_by")
//	private String upInstantBy;
//
//	@Field("geoLocation")
//	private FeatureCollection geoLocation;
//
//	@Field("dmas")
//	private List<String> dmas;
//
//	@Field("tenant_types")
//	private List<TenantType> tenantTypes;
//
//	@Field("is_buyer")
//	private boolean buyer;
//
//	@Field("is_seller")
//	private boolean seller;
//
//	@Field("selected_tags")
//	private List<String> selectedTags;
//
//	@Field("account_type")
//	private String accountType;
//
//	@Field("account_holder_name")
//	private String accountHolderName;
//
//	@Field("account_num")
//	private String accountNum;
//
//	@Field("swift_code")
//	private String swiftCode;
//
//	@Field("routing_num")
//	private String routingNum;
//
//	@Field("telephone_num")
//	private String telephoneNum;
//
//	// products on which tenant is interested
//	@Field("interested_products")
//	private Set<String> interestedProducts = new HashSet<>();
//
//	@Field("address")
//	private Address address;
//
//	@Field("active")
//	private boolean active = true;
//
//	@Field("currency")
//	private Currency currency;
//
//	@Field("currenciesSupported")
//	private List<Currency> currenciesSupported;
//
//}
