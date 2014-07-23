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

import com.rill.rest.sign.OAuthApiRequestSigner;
import com.rill.rest.sign.EncryptionAlgorithm;

import com.rill.api.RillApiParam;
import com.rill.api.RillAuthParam;
import com.rill.api.OAuthParam;

public class OAuthClient extends BaseRillClient {

    private static final Logger log = LoggerFactory.getLogger(OAuthClient.class);

    private String nonce;
    private String oauthVersion;

    public OAuthClient(final String url){
        super(url);
        encryptionAlgorithm = EncryptionAlgorithm.HMAC_SHA1_ALGORITHM;
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
        final StringBuilder authString = new StringBuilder(OAuthParam.OAUTH)
            .append(" ")
            .append(OAuthParam.OAUTH_CONSUMER_KEY).append("=").append(apiKey).append(", ")
            .append(OAuthParam.OAUTH_NONCE).append("=").append(nonce).append(", ")
            .append(OAuthParam.OAUTH_SIGNATURE).append("=").append(signature).append(", ")
            .append(OAuthParam.OAUTH_SIGNATURE_METHOD).append("=").append(encryptionAlgorithm.getName()).append(", ")
            .append(OAuthParam.OAUTH_TIMESTAMP).append("=").append(timestamp);
        if(oauthVersion!=null){
            authString.append(", ").append(OAuthParam.OAUTH_VERSION).append("=").append(oauthVersion);
        }
        request.header(OAuthParam.AUTHORIZATION_HEADER, authString.toString());
    }

    protected String getSignature(String timestamp){
        OAuthApiRequestSigner.Builder signer = new OAuthApiRequestSigner.Builder()
            .withEncryptionAlgorithm(this.encryptionAlgorithm)
            .withMethod(this.method)
            .withUrl(this.url)
            .withEncryptionKey(this.secret+"&")
            .withParameterValue(OAuthParam.OAUTH_CONSUMER_KEY.getName(), this.apiKey)
            .withParameterValue(OAuthParam.OAUTH_NONCE.getName(), this.nonce)
            .withParameterValue(OAuthParam.OAUTH_SIGNATURE_METHOD.getName(), this.encryptionAlgorithm.getName())
            .withParameterValue(OAuthParam.OAUTH_TIMESTAMP.getName(), timestamp);
        //.withParameterValue(RillAuthParam.TIMESTAMP.getName(), timestamp);
        addCommonSignatureParams(signer);
        return signer.sign();
    }
    
    @Override
    protected void validateExtraParameters(){
        throwIfNotPresent(this.nonce, "Nonce", "withNonce()");
    }

}
