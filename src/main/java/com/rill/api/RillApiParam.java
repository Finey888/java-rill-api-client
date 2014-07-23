package com.rill.api;

public enum RillApiParam {

    MOBILE_NUMBER("mobile"), 
    PHONE_NUMBER("phone"), 
    EMAIL("email"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    DOB("dob"),
    KEYWORD("keyword"), 
    PARTNER_ACRONYM("partnerAcronym");

    private String name;

    private RillApiParam(String name){
        this.name=name;
    }
    
    public String getName(){
        return name;
    }
}
