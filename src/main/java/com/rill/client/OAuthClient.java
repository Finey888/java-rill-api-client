package com.rill.client;

import java.util.LinkedList;
import java.util.List;


import com.rill.api.RillRestResponse; 
//import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mashape.unirest.request.HttpRequestWithBody;

import com.rill.rest.sign.ClassicApiRequestSigner;
import com.rill.rest.sign.OAuthApiRequestSigner;

import static com.rill.api.RillApiConstants.*;
import static com.rill.api.RillAuthConstants.*;
import static com.rill.api.OAuthConstants.*;

public class OAuthClient extends BaseRillClient {

    private static final Logger log = LoggerFactory.getLogger(OAuthClient.class);

    private String nonce;
    private String oauthVersion;

    public OAuthClient(){
	super();
	encryptionAlgorithm = ClassicApiRequestSigner.EncryptionAlgorithm.HMAC_SHA1_ALGORITHM;
    }

    public OAuthClient withNonce(final String nonce){
	this.nonce=nonce;
	return this;
    }
    public OAuthClient withOauthVersion(final String version){
	this.oauthVersion=version;
	return this;
    }

    @Override
    protected void addAuth(final HttpRequestWithBody request, final String timestamp) {
	final String signature = getSignature(timestamp);
	final StringBuilder authString = new StringBuilder(OAUTH)
	    .append(" ")
	    .append(OAUTH_CONSUMER_KEY).append("=").append(apiKey).append(", ")
	    .append(OAUTH_NONCE).append("=").append(nonce).append(", ")
	    .append(OAUTH_SIGNATURE).append("=").append(signature).append(", ")
	    .append(OAUTH_SIGNATURE_METHOD).append("=").append(encryptionAlgorithm.getName()).append(", ")
	    .append(OAUTH_TIMESTAMP).append("=").append(timestamp);
	if(oauthVersion!=null){
	    authString.append(", ").append(OAUTH_VERSION).append("=").append(oauthVersion);
	}
	request.header(AUTHORIZATION_HEADER, authString.toString());
    }

    protected String getSignature(String timestamp){
	OAuthApiRequestSigner.Builder signer = new OAuthApiRequestSigner.Builder()
	    .withEncryptionAlgorithm(this.encryptionAlgorithm)
	    .withMethod(this.method)
	    .withUrl(this.url)
	    .withEncryptionKey(this.secret+"&")
	    .withParameterValue(OAUTH_CONSUMER_KEY, this.apiKey)
	    .withParameterValue(OAUTH_NONCE, this.nonce)
	    .withParameterValue(OAUTH_SIGNATURE_METHOD, this.encryptionAlgorithm.getName())
	    .withParameterValue(OAUTH_TIMESTAMP, timestamp)
	    //RESOLVE refactor- common for all authentication methods
	    .withParameterValue(TIMESTAMP_PARAM_NAME, timestamp);
	
	for(String identifier : this.membershipIdentifiers){
	    signer.withParameterValue(MEMBERSHIP_ID_PARAM_NAME, identifier);
	}
	for(String keyword : this.keywords){
	    signer.withParameterValue(KEYWORD_PARAM_NAME, keyword);
	}
	if(this.verificationTargetAcronym!=null){
	    signer.withParameterValue(PARTNER_ACRONYM_PARAM_NAME, this.verificationTargetAcronym);
	}

	return signer.sign();
    }
    
    @Override
    protected void validateExtraParameters(){
	throwIfNotPresent(this.nonce, "Nonce", "withNonce()");
    }

}
