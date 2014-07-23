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
import com.rill.rest.sign.EncryptionAlgorithm;

import com.rill.api.RillApiParam;
import com.rill.api.RillAuthParam;
import com.rill.api.OAuthParam;

public class SignatureAuthClient extends BaseRillClient {

    private static final Logger log = LoggerFactory.getLogger(SignatureAuthClient.class);

    public SignatureAuthClient(final String url){
        super(url);
        encryptionAlgorithm = EncryptionAlgorithm.HMAC_SHA256_ALGORITHM; //default
    }

    @Override
    protected void addAuth(final HttpRequestWithBody request, final String timestamp) {
        request
            .field(RillAuthParam.API_KEY.getName(), this.apiKey)
            .field(RillAuthParam.SIGNATURE.getName(), this.encryptionAlgorithm.getName())
            .field(RillAuthParam.TIMESTAMP.getName(), timestamp)
            .field(RillAuthParam.SIGNATURE.getName(), getSignature(timestamp));
    }
    
    protected String getSignature(String timestamp){
        ClassicApiRequestSigner.Builder signer = new ClassicApiRequestSigner.Builder()
            .withEncryptionAlgorithm(this.encryptionAlgorithm)
            .withEncryptionKey(this.secret)
            .withParameterValue(RillAuthParam.API_KEY.getName(), this.apiKey)
            .withParameterValue(RillAuthParam.SIGNATURE.getName(), this.encryptionAlgorithm.getName())
            .withParameterValue(RillAuthParam.TIMESTAMP.getName(), timestamp);
        addCommonSignatureParams(signer);
        return signer.sign();
    }
    
    @Override
    protected void validateExtraParameters(){
        //do nothing
    }

}
