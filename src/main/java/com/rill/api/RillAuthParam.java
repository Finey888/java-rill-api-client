package com.rill.api;

import com.rill.rest.sign.ClassicApiRequestSigner;

public enum RillAuthParam {

    API_KEY("api-key"),
    SIGNATURE("sig"),
    SIGNATURE_METHOD(ClassicApiRequestSigner.SIGNATURE_METHOD_PARAM_NAME),
    TIMESTAMP("timestamp");

    private String name;

    private RillAuthParam(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
