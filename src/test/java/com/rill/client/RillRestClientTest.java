package com.rill.client;

import com.rill.api.RillRestResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Test;

//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.client.RestClientException;


public class RillRestClientTest {
    
    @org.junit.Ignore
    @Test
    public void testOauthClient() throws Exception {

	OAuthClient client = new OAuthClient();
	RillRestResponse rrr = client
	    .withNonce("1234567")
	    .withApiKey("12345")
	    .withSecret("abcde")
	    .withMemberIdentifier("4155316334")
	    .withVerificationTargetAcronym("ANA")
	    .verifyMembership();
	System.out.println(rrr);
	
        // final DateTime fixedTestDt = new DateTime(2014, 2, 28, 18, 30);

        // DateTimeUtils.setCurrentMillisFixed(fixedTestDt.getMillis());

        // final String
        //     apiKey = "My Api Key",
        //     signKey = "My Sign Key",
        //     acronym = "ORG",
        //     number = "12345678900";

        // final String expectedUrlStr = "https://www.rillate.com/api/mqs/v1/query/membership?apiKey=My+Api+Key&identifier=ORG&partnerAcronym=12345678900&signatureMethod=HmacSHA256&timestamp=1393612200000&sig=B6JHPTl+X4G4MZgE7YqIpeFBctJp6k3c20rXymJ+Hkg=";

        // RillRestClient client = new RillRestClient(apiKey, signKey);
        // RestTemplate mockRestTemplate = mock(RestTemplate.class);
        // client.setRestTemplate(mockRestTemplate);

        // client.verifyMembership(acronym, number);

        // verify(mockRestTemplate).getForObject(expectedUrlStr, RillRestResponse.class);

        // DateTimeUtils.setCurrentMillisSystem();
    }
}
