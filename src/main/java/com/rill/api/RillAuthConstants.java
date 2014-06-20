package com.rill.api;

import com.rill.rest.sign.ClassicApiRequestSigner;

public interface RillAuthConstants {    
    String API_KEY_PARAM_NAME = "apiKey";
    String SIGNATURE_PARAM_NAME = "sig";
    String SIGNATURE_METHOD_PARAM_NAME = ClassicApiRequestSigner.SIGNATURE_METHOD_PARAM_NAME;
    String TIMESTAMP_PARAM_NAME = "timestamp";
}
