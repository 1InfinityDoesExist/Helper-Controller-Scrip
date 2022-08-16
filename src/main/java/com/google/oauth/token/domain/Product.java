//package com.google.oauth.token.domain;
//
//import java.io.Serializable;
//import java.time.Instant;
//import java.util.Currency;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.Set;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.gaian.marketplace.domain.MasterConfig;
//import com.gaian.marketplace.domain.PlpConfiguration;
//import com.gaian.marketplace.enums.Category;
//import com.gaian.marketplace.enums.ProductStatus;
//
//import lombok.Data;
//
///**
// * A Product.
// */
//
//@Data
//@Document(collection = "product")
//public class Product implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	private String id;
//
//	@Field("name")
//	private String name;
//
//	@Field("description")
//	private String description;
//
//	@Field("product_types")
//	private List<String> productTypes;
//
//	@Field("logo_url")
//	private String logoUrl;
//
//	@Field("tags")
//	private Set<String> tags;
//
//	@Field("masterConfig")
//	private Map<String, MasterConfig> masterConfig;
//
//	@Field("serving_areas")
//	private List<String> servingAreas;
//
//	@Field("snapshots_urls")
//	private Set<String> snapshotsUrls;
//
//	@Field("active_since")
//	private Instant activeSince;
//
//	@Field("related_information")
//	private String relatedInformation;
//
//	@Field("attached_documents_urls")
//	private Set<String> attachedDocumentsUrls;
//
//	@Field("video_urls")
//	private Set<String> videoUrls;
//
//	@Field("website")
//	private String website;
//
//	@Field("created_on")
//	private Instant createdOn;
//
//	@Field("updated_on")
//	private Instant upInstantOn;
//
//	@Field("updated_by")
//	private String updatedBy;
//
//	@Field("allows_multiple_purchases")
//	private Boolean allowsMultiplePurchases;
//	@Field("active")
//	private boolean active = true;
//	@Field("product_roles")
//	private List<String> productRoles;
//	@Field("config")
//	private Map<String, Object> config;
//	@DBRef
//	@Field("providedByTenant")
//	@JsonIgnoreProperties("products")
//	private Tenant providedByTenant;
//	@Field("version")
//	private String version;
//	@Field("productUrl")
//	private String productUrl;
//	@Field("hostedApps")
//	private List<String> hostedApps;
//	@Field("domain")
//	private String domain;
//	@Field("allianceInfo")
//	private Map<String, String> allianceInfo = null;
//	// tenants who are interested on this product
//	@Field("interestedTenants")
//	private Set<String> interestedTenants = new HashSet<>();
//
//	@Field("productStatus")
//	private ProductStatus productStatus = ProductStatus.PENDING_AT_SU;
//
//	@Field("currency")
//	private Currency currency;
//
//	@Field("category")
//	private Category category;
//
//	@Field("properties")
//	private Properties properties;
//
//	@Field("plpConfiguration")
//	private PlpConfiguration plpConfiguration;
//
//}
