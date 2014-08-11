package com.rill.client;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.rill.api.RillRestResponse; 
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.codehaus.jackson.map.ObjectMapper;
//import org.apache.http.client.utils.URIBuilder;

//import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.JsonNode;

import com.rill.rest.sign.ClassicApiRequestSigner;
import com.rill.rest.sign.OAuthApiRequestSigner;
import com.rill.rest.sign.EncryptionAlgorithm;
import com.rill.rest.sign.SignatureBuilder;

import com.rill.api.RillApiParam;
import com.rill.api.RillAuthParam;

public abstract class BaseRillClient {

    private static final Logger log = LoggerFactory.getLogger(BaseRillClient.class);
    
    protected EncryptionAlgorithm encryptionAlgorithm = EncryptionAlgorithm.HMAC_SHA256_ALGORITHM;
    protected String url = "https://www.rillate.com/api/mqs/v1/query";
    protected String method = "GET";
    protected String apiKey;
    protected String secret;
    protected String mobile;
    protected String landline;
    protected String email;
    protected String firstName;
    protected String lastName;
    protected String dateOfBirth;
    protected Map<String, String> keywords = new HashMap();
    protected String verificationTargetAcronym;
    //protected URIBuilder uriBuilder = new URIBuilder();

    BaseRillClient(final String url){
        this.url=url;
	//this.uriBuilder = new URIBuilder(url);
    }
    public BaseRillClient withApiKey(final String apiKey){
        this.apiKey=apiKey;
        return this;
    }
    public BaseRillClient withSecret(final String secret){
        this.secret=secret;
        return this;
    }
    public BaseRillClient withMobilePhoneNumber(final String number){
        this.mobile=number; //RESOLVE share validation for identifiers with backend to avoid network roundtrip
        return this;
    }

    public BaseRillClient withPhoneNumber(final String number){
        this.landline=number; 
        return this;
    }

    public BaseRillClient withEmail(final String email){
        this.email=email;
        return this;
    }

    public BaseRillClient withFirstName(final String firstName){
        this.firstName=firstName;
        return this;
    }

    public BaseRillClient withLastName(final String lastName){
        this.lastName=lastName;
        return this;
    }

    public BaseRillClient withDateOfBirth(final String dateOfBirth){
        this.dateOfBirth=dateOfBirth; 
        return this;
    }
    
    public BaseRillClient withKeyword(final String attribute, final String keyword){
        this.keywords.put(attribute, keyword);
        return this;
    }

    public BaseRillClient withVerificationTargetAcronym(final String acronym){
        this.verificationTargetAcronym=acronym;
        return this;
    }

    GetRequest getHttpRequest(){
        validate();
	
        final Long timestamp = new DateTime().getMillis();
	GetRequest request = Unirest.get(url);
        addAuth(request, timestamp);
        
        if(this.mobile!=null){
	    log.debug("setting {} to {}", RillApiParam.MOBILE_NUMBER.getName(), this.mobile);
            request.field(RillApiParam.MOBILE_NUMBER.getName(), this.mobile);
	    //uriBuilder.addParameter(RillApiParam.MOBILE_NUMBER.getName(), this.mobile);
        }

        if(this.landline!=null){
	    log.debug("setting {} to {}", RillApiParam.PHONE_NUMBER.getName(), this.landline);
            request.field(RillApiParam.PHONE_NUMBER.getName(), this.landline);
            //uriBuilder.addParameter(RillApiParam.PHONE_NUMBER.getName(), this.landline);
        }

        if(this.email!=null){
	    log.debug("setting {} to {}", RillApiParam.EMAIL.getName(), this.email);
            request.field(RillApiParam.EMAIL.getName(), this.email);
	    //uriBuilder.addParameter(RillApiParam.EMAIL.getName(), this.email);
        }

        if(this.firstName!=null){
	    log.debug("setting {} to {}", RillApiParam.FIRST_NAME.getName(), this.firstName);
            request.field(RillApiParam.FIRST_NAME.getName(), this.firstName);
	    //uriBuilder.addParameter(RillApiParam.FIRST_NAME.getName(), this.firstName);
        }

        if(this.lastName!=null){
	    log.debug("setting {} to {}", RillApiParam.LAST_NAME.getName(), this.lastName);
            request.field(RillApiParam.LAST_NAME.getName(), this.lastName);
	    //uriBuilder.addParameter(RillApiParam.LAST_NAME.getName(), this.lastName);
        }

        if(this.dateOfBirth!=null){
	    log.debug("setting {} to {}", RillApiParam.DOB.getName(), this.dateOfBirth);
            request.field(RillApiParam.DOB.getName(), this.dateOfBirth);
	    //uriBuilder.addParameter(RillApiParam.DOB.getName(), this.dateOfBirth);
        }

        for(String keyword : this.keywords.keySet()){
	    log.debug("setting {} to {}", keyword, this.keywords.get(keyword));
            request.field(keyword, this.keywords.get(keyword));
	    //uriBuilder.addParameter(keyword, this.keywords.get(keyword));
        }
        if(this.verificationTargetAcronym!=null){
	    log.debug("setting {} to {}", RillApiParam.PARTNER_ACRONYM.getName(), this.verificationTargetAcronym);
            request.field(RillApiParam.PARTNER_ACRONYM.getName(), this.verificationTargetAcronym);
	    //uriBuilder.addParameter(RillApiParam.PARTNER_ACRONYM.getName(), this.verificationTargetAcronym);
        }
	log.debug("setting {} to {}", RillAuthParam.TIMESTAMP.getName(), timestamp);
        request.field(RillAuthParam.TIMESTAMP.getName(), timestamp);
	//uriBuilder.addParameter(RillAuthParam.TIMESTAMP.getName(), timestamp);
        //HttpRequest request = Unirest.get(uriBuilder.build().toString());

	return request;
    }

    public RillRestResponse verifyMembership() throws Exception {
	//HttpRequestWithBody request = getHttpRequest();
	GetRequest request = getHttpRequest();
        HttpResponse<JsonNode> jsonResponse = request.asJson();
        log.debug("response json "+jsonResponse.getBody());
        return new ObjectMapper().readValue(jsonResponse.getBody().getObject().toString(), RillRestResponse.class);
    }

    protected void validate(){
        throwIfNotPresent(this.url, "Url", "withUrl()");
        throwIfNotPresent(this.method, "Http method", "withMethod()");
        throwIfNotPresent(this.apiKey, "API key", "withApiKey()");
        throwIfNotPresent(this.secret, "Secret", "withSecret()");
        validateExtraParameters();

    }

    protected void throwIfNotPresent(final String value, final String descr, final String setter){
    	if(value==null){
    	    throw new RuntimeException(descr+" required, please specify with "+setter);
    	}
    }
    protected void throwIfNotPresent(final List<String> values, final String descr, final String setter){
    	if(values==null||values.size()==0){
    	    throw new RuntimeException(descr+" required, please specify with "+setter);
    	}
    }

    protected void throwIfNotPresent(final String string, final List<String> list, final String descr, final String setter){
        if(string==null && (list==null || list.size()==0)){
            throw new RuntimeException(descr+" required, please specify with "+setter);
        }
    }

    protected void addCommonSignatureParams(SignatureBuilder signer, Long timestamp){
                    
        if(this.mobile!=null){
            signer.withParameterValue(RillApiParam.MOBILE_NUMBER.getName(), this.mobile);
        }

        if(this.landline!=null){
            signer.withParameterValue(RillApiParam.PHONE_NUMBER.getName(), this.landline);
        }

        if(this.email!=null){
            signer.withParameterValue(RillApiParam.EMAIL.getName(), this.email);
        }

        if(this.firstName!=null){
            signer.withParameterValue(RillApiParam.FIRST_NAME.getName(), this.firstName);
        }

        if(this.lastName!=null){
            signer.withParameterValue(RillApiParam.LAST_NAME.getName(), this.lastName);
        }

        if(this.dateOfBirth!=null){
            signer.withParameterValue(RillApiParam.DOB.getName(), this.dateOfBirth);
        }

        for(String keyword : this.keywords.keySet()){
            signer.withParameterValue(keyword, this.keywords.get(keyword));
        }
        if(this.verificationTargetAcronym!=null){
            signer.withParameterValue(RillApiParam.PARTNER_ACRONYM.getName(), this.verificationTargetAcronym);
        }

	signer.withParameterValue(RillAuthParam.TIMESTAMP.getName(), String.valueOf(timestamp));

    }
    //protected abstract void addAuth(HttpRequestWithBody request, String timestamp);
    protected abstract void addAuth(GetRequest request, Long timestamp);
    protected abstract void validateExtraParameters();
}
