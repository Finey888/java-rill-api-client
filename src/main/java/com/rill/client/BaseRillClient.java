package com.rill.client;

import java.util.LinkedList;
import java.util.List;

import com.rill.api.RillRestResponse; 
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.ObjectMapper;

import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.JsonNode;

import com.rill.rest.sign.ClassicApiRequestSigner;
import com.rill.rest.sign.OAuthApiRequestSigner;

import static com.rill.api.RillApiConstants.*;
import static com.rill.api.RillAuthConstants.*;
import static com.rill.api.OAuthConstants.*;

public abstract class BaseRillClient {

    private static final Logger log = LoggerFactory.getLogger(BaseRillClient.class);
    
    protected ClassicApiRequestSigner.EncryptionAlgorithm encryptionAlgorithm = ClassicApiRequestSigner.EncryptionAlgorithm.HMAC_SHA256_ALGORITHM;
    protected String url = "https://www.rillate.com/api/mqs/v1/query";
    protected String method = "POST";
    protected String apiKey;
    protected String secret;
    protected List<String> membershipIdentifiers = new LinkedList();
    protected List<String> keywords = new LinkedList();
    protected String verificationTargetAcronym;
    
    public BaseRillClient withApiKey(final String apiKey){
	this.apiKey=apiKey;
	return this;
    }
    public BaseRillClient withSecret(final String secret){
	this.secret=secret;
	return this;
    }
    public BaseRillClient withMemberIdentifier(final String memberIdentifier){
	this.membershipIdentifiers.add(memberIdentifier);
	return this;
    }
    public BaseRillClient withKeyword(final String keyword){
	if(this.verificationTargetAcronym!=null){
	    throw new IllegalStateException("Can't mix keywords with direct verification target");
	}
	this.keywords.add(keyword);
	return this;
    }
    public BaseRillClient withVerificationTargetAcronym(final String acronym){
	if(keywords.size() > 0){
	    throw new IllegalStateException("Can't mix keywords with direct verification target");
	}
	this.verificationTargetAcronym=acronym;
	return this;
    }
    public RillRestResponse verifyMembership() throws Exception {
	
	validate();

	final String timestamp = String.valueOf(new DateTime().getMillis());
	HttpRequestWithBody request = Unirest.post(url);

	addAuth(request, timestamp);

	for(String identifier : this.membershipIdentifiers){
	    request.field(MEMBERSHIP_ID_PARAM_NAME, identifier);
	}
	for(String keyword : this.keywords){
	    request.field(KEYWORD_PARAM_NAME, keyword);
	}
	if(this.verificationTargetAcronym!=null){
	    request.field(PARTNER_ACRONYM_PARAM_NAME, this.verificationTargetAcronym);
	}
	request.field(TIMESTAMP_PARAM_NAME, timestamp);
	HttpResponse<JsonNode> jsonResponse = request.asJson();
	log.debug("response json "+jsonResponse.getBody());
	return new ObjectMapper().readValue(jsonResponse.getBody().getObject().toString(), RillRestResponse.class);
    }

    protected void validate(){
	throwIfNotPresent(this.url, "Url", "withUrl()");
	throwIfNotPresent(this.method, "Http method", "withMethod()");
	throwIfNotPresent(this.apiKey, "API key", "withApiKey()");
	throwIfNotPresent(this.secret, "Secret", "withSecret()");
	throwIfNotPresent(this.membershipIdentifiers, "One or more member identifiers", "withMemberIdentifier()");
	throwIfNotPresent(this.verificationTargetAcronym, this.keywords, "Direct verification target (acronym) or one or more keywords", 
			  "withKeyword() or withVerificationTargetAcronym()");
	validateExtraParameters();

    }

    protected void throwIfNotPresent(final String value, final String descr, final String setter){
    	if(value==null){
    	    throw new RuntimeException(descr+" required, please specify with "+setter);
    	}
    }
    protected void throwIfNotPresent(final List<String> values, final String descr, final String setter){
    	if(values==null||values.size()==0){
    	    throw new RuntimeException(descr+" required, please specify with "+setter);
    	}
    }

    protected void throwIfNotPresent(final String string, final List<String> list, final String descr, final String setter){
	if(string==null && (list==null || list.size()==0)){
	    throw new RuntimeException(descr+" required, please specify with "+setter);
	}
    }
    protected abstract void addAuth(HttpRequestWithBody request, String timestamp);
    protected abstract void validateExtraParameters();
}
