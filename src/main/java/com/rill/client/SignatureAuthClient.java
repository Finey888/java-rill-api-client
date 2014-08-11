package com.rill.client;

import java.util.LinkedList;
import java.util.List;


import com.rill.api.RillRestResponse; 
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.client.utils.URIBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mashape.unirest.request.GetRequest;

import com.rill.rest.sign.ClassicApiRequestSigner;
import com.rill.rest.sign.OAuthApiRequestSigner;
import com.rill.rest.sign.EncryptionAlgorithm;

import com.rill.api.RillAuthParam;

public class SignatureAuthClient extends BaseRillClient {

    private static final Logger log = LoggerFactory.getLogger(SignatureAuthClient.class);

    public SignatureAuthClient(final String url){
        super(url);
        encryptionAlgorithm = EncryptionAlgorithm.HMAC_SHA256_ALGORITHM; //default
    }

    @Override
    protected void addAuth(final GetRequest request, final Long timestamp) {
        request
            .field(RillAuthParam.API_KEY.getName(), this.apiKey)
            //.field(RillAuthParam.TIMESTAMP.getName(), timestamp) //parent appends
	    .field(RillAuthParam.SIGNATURE_METHOD.getName(), encryptionAlgorithm.getName())
            .field(RillAuthParam.SIGNATURE.getName(), getSignature(timestamp));
    }
    
    protected String getSignature(Long timestamp){
        ClassicApiRequestSigner.Builder signer = new ClassicApiRequestSigner.Builder()
            .withEncryptionAlgorithm(this.encryptionAlgorithm)
            .withEncryptionKey(this.secret)
            .withParameterValue(RillAuthParam.API_KEY.getName(), this.apiKey);
	//.withParameterValue(RillAuthParam.TIMESTAMP.getName(), timestamp); parent appends
        addCommonSignatureParams(signer, timestamp);
        return signer.sign();
    }
    
    @Override
    protected void validateExtraParameters(){
        //do nothing
    }

}
