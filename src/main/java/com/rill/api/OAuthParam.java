package com.rill.api;

import com.rill.rest.sign.OAuthApiRequestSigner;

public enum OAuthParam {
    
    OAUTH_CONSUMER_KEY("oauth_consumer_key"),
    OAUTH_NONCE("oauth_nonce"),
    OAUTH_SIGNATURE("oauth_signature"),
    OAUTH_SIGNATURE_METHOD(OAuthApiRequestSigner.OAUTH_SIGNATURE_METHOD),
    OAUTH_TIMESTAMP("oauth_timestamp"),
    OAUTH_VERSION("oauth_version");
    
    public static String OAUTH = "OAuth";
    public static String AUTHORIZATION_HEADER = "authorization";
    private String name;

    private OAuthParam(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
