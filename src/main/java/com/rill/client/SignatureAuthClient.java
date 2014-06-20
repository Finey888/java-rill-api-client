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

public class SignatureAuthClient extends BaseRillClient {

    private static final Logger log = LoggerFactory.getLogger(SignatureAuthClient.class);

    public SignatureAuthClient(){
	super();
	encryptionAlgorithm = ClassicApiRequestSigner.EncryptionAlgorithm.HMAC_SHA256_ALGORITHM; //default
    }

    @Override
    protected void addAuth(final HttpRequestWithBody request, final String timestamp) {
	request
	    .field(API_KEY_PARAM_NAME, this.apiKey)
	    .field(SIGNATURE_METHOD_PARAM_NAME, this.encryptionAlgorithm.getName())
	    .field(TIMESTAMP_PARAM_NAME, timestamp)
	    .field(SIGNATURE_PARAM_NAME, getSignature(timestamp));
    }
    
    protected String getSignature(String timestamp){
	ClassicApiRequestSigner.Builder signer = new ClassicApiRequestSigner.Builder()
	    .withEncryptionAlgorithm(this.encryptionAlgorithm)
	    .withEncryptionKey(this.secret)
	    .withParameterValue(API_KEY_PARAM_NAME, this.apiKey)
	    .withParameterValue(SIGNATURE_METHOD_PARAM_NAME, this.encryptionAlgorithm.getName())
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
	//do nothing
    }

}
