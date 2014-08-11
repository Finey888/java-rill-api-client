package com.rill.client;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import com.rill.api.RillRestResponse; 
//import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mashape.unirest.request.GetRequest;

import com.rill.rest.sign.OAuthApiRequestSigner;
import com.rill.rest.sign.EncryptionAlgorithm;

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
    protected void addAuth(final GetRequest request, final Long timestamp) {
	final Long timestampSeconds = timestamp/1000L;
        final String signature = getSignature(timestampSeconds, timestamp);
        final StringBuilder authString = new StringBuilder(OAuthParam.OAUTH)
            .append(" ")
            .append(URLEncoder.encode(OAuthParam.OAUTH_CONSUMER_KEY.getName())).append("=").append(URLEncoder.encode(apiKey)).append(", ")
            .append(URLEncoder.encode(OAuthParam.OAUTH_NONCE.getName())).append("=").append(URLEncoder.encode(nonce)).append(", ")
            .append(URLEncoder.encode(OAuthParam.OAUTH_SIGNATURE.getName())).append("=").append(URLEncoder.encode(signature)).append(", ")
            .append(URLEncoder.encode(OAuthParam.OAUTH_SIGNATURE_METHOD.getName())).append("=").append(URLEncoder.encode(encryptionAlgorithm.getName())).append(", ")
            .append(URLEncoder.encode(OAuthParam.OAUTH_TIMESTAMP.getName())).append("=").append(String.valueOf(timestampSeconds));
        if(oauthVersion!=null){
            authString.append(", ").append(URLEncoder.encode(OAuthParam.OAUTH_VERSION.getName())).append("=").append(URLEncoder.encode(oauthVersion));
        }

        request.header(OAuthParam.AUTHORIZATION_HEADER, authString.toString());
    }

    protected String getSignature(Long timestampSeconds, Long timestamp){
        OAuthApiRequestSigner.Builder signer = new OAuthApiRequestSigner.Builder()
            .withEncryptionAlgorithm(this.encryptionAlgorithm)
            .withMethod(this.method)
            .withUrl(this.url)
            .withEncryptionKey(this.secret+"&")
            .withParameterValue(OAuthParam.OAUTH_CONSUMER_KEY.getName(), this.apiKey)
            .withParameterValue(OAuthParam.OAUTH_NONCE.getName(), this.nonce)
            .withParameterValue(OAuthParam.OAUTH_SIGNATURE_METHOD.getName(), this.encryptionAlgorithm.getName())
            .withParameterValue(OAuthParam.OAUTH_TIMESTAMP.getName(), String.valueOf(timestampSeconds));
        addCommonSignatureParams(signer, timestamp);
        return signer.sign();
    }
    
    @Override
    protected void validateExtraParameters(){
        throwIfNotPresent(this.nonce, "Nonce", "withNonce()");
    }

}
