package com.rill.api;

import com.rill.rest.sign.OAuthApiRequestSigner;

public interface OAuthConstants {
    String OAUTH = "OAuth";
    String AUTHORIZATION_HEADER = "authorization";
    String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
    String OAUTH_NONCE = "oauth_nonce";
    String OAUTH_SIGNATURE = "oauth_signature";
    String OAUTH_SIGNATURE_METHOD = OAuthApiRequestSigner.OAUTH_SIGNATURE_METHOD;
    String OAUTH_TIMESTAMP = "oauth_timestamp";
    String OAUTH_VERSION = "oauth_version";
}
