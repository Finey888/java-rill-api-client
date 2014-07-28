package com.rill.api;

public enum RillApiParam {

    MOBILE_NUMBER("mobile"), 
    PHONE_NUMBER("phone"), 
    EMAIL("email"),
    FIRST_NAME("first-name"),
    LAST_NAME("last-name"),
    DOB("dob"),
    KEYWORD("keyword"), 
    PARTNER_ACRONYM("partner-acronym");

    private String name;

    private RillApiParam(String name){
        this.name=name;
    }
    
    public String getName(){
        return name;
    }
}
