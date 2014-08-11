package com.rill.client;

import java.util.Map;
import java.util.List;

import com.mashape.unirest.request.GetRequest;

import com.rill.api.RillApiParam;
import com.rill.api.RillAuthParam;
import com.rill.api.OAuthParam;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineClient {

    public static final String TYPE_PARAM = "type";
    public static final String OAUTH_TYPE = "oauth";
    public static final String SIG_TYPE = "sig";
    public static final String URL_PARAM = "url";
    public static final String SECRET_PARAM = "secret";

    public static void main(String[] args) throws Exception {
	CommandLineClient clc = new CommandLineClient(args);
	String requestUrl = clc.getRequestUrl();
	Map<String, List<String>> headers = clc.getHeaders();
	System.out.println(getCurlString(requestUrl, headers));
    }
    
    public static String getCurlString(final String url, final Map<String, List<String>> headers){
	StringBuilder sb = new StringBuilder();
	sb.append("curl --insecure ");
	if(headers!=null && headers.size()>0){
	    sb.append(" -H \"");
	    for(String header : headers.keySet()){
		sb.append(header).append(": ");
		String comma = "";
		for(String value : headers.get(header)){
		    sb.append(comma).append(value);
		    comma = ", ";
		}
	    }
	    sb.append("\" ");
	}
	sb.append("\"").append(url).append("\"");
	return sb.toString();
    }

    private GetRequest request;

    public CommandLineClient(final String[] args) throws Exception {
	
	Options options = getTypeOptions();

	CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse(options, new String[]{args[0], args[1]}, false);
        String type = cmd.getOptionValue(TYPE_PARAM);

	options = getOptions();
	String url = null;

	String[] clientArgs = new String[args.length-2];
	System.arraycopy(/*src=*/args, 2,
			 /*dest=*/clientArgs, 0,
			 clientArgs.length);

	BaseRillClient client = null;
	
	if(OAUTH_TYPE.equals(type)){
	    addOAuthOptions(options);
	    addApiOptions(options);
	    cmd = parser.parse(options, clientArgs, false);
	    url = cmd.getOptionValue(URL_PARAM);
	    client = new OAuthClient(url);
	    setupOauthParams(client, cmd);
	    setupApiParams(client, cmd);
	    setupKeywords(client, cmd);
	    System.out.println(cmd);
	}else if(SIG_TYPE.equals(type)){
	    addSignatureOptions(options);
	    addApiOptions(options);
	    cmd = parser.parse(options, clientArgs, false);
	    url = cmd.getOptionValue(URL_PARAM);
	    System.out.println(cmd.getArgList());
	    System.out.println(cmd.getOptions().length+" options parsed");
	    for(Option op : cmd.getOptions()){
		System.out.println(op.getOpt()+"="+op.getValue());
	    }
	    client = new SignatureAuthClient(url);
	    setupSignatureParams(client, cmd);
	    setupApiParams(client, cmd);
	    setupKeywords(client, cmd);
	    
	}else{
	    throw new IllegalArgumentException("unrecognized value for "+TYPE_PARAM);
	}
	this.request = client.getHttpRequest();
    }

    public String getRequestUrl() throws Exception{
	return this.request.getUrl();
    }

    public Map<String, List<String>> getHeaders(){
	return this.request.getHeaders();
    }
    
    private Options getTypeOptions(){
	Options options = new Options();
        options.addOption(TYPE_PARAM, /*arg=*/true, "oauth or sig");
	return options;
    }	

    private Options getOptions(){
	Options options = new Options();
	options.addOption(SECRET_PARAM, /*isArg=*/true, "secret for signing");
	options.addOption(URL_PARAM, /*isArg=*/true, "api host url");
	return options;
    }	

    private void addOAuthOptions(final Options options){
	for(OAuthParam param : OAuthParam.values()){
	    options.addOption(param.getCommandLineSafeName(), /*isArg=*/true, "oauth param "+param.getCommandLineSafeName());
	}
    }

    private void addSignatureOptions(final Options options){
	for(RillAuthParam param : RillAuthParam.values()){
	    options.addOption(param.getCommandLineSafeName(), /*isArg=*/true, "rill auth param "+param.getCommandLineSafeName());
	}
    }

    private void addApiOptions(final Options options){
	for(RillApiParam param : RillApiParam.values()){
	    options.addOption(param.getCommandLineSafeName(), /*isArg=*/true, "api param "+param.getCommandLineSafeName());
	}
    }
    
    private void setupKeywords(final BaseRillClient client, final CommandLine cmd){
	String attr=null;
	for(String str : cmd.getArgs()){ //assume what wasn't recognized as standard parameters was keywords
	    if(attr==null){
		attr = str;
	    }else{
		client.withKeyword(attr, str);
		attr=null;
	    }
	}
    }

    private void setupOauthParams(final BaseRillClient client, final CommandLine cmd){
	//RESOLVE make CommandLineClient parameterized with BaseRillClient subtype
	OAuthClient oauthClient = (OAuthClient)client;
	oauthClient.withApiKey(cmd.getOptionValue(OAuthParam.OAUTH_CONSUMER_KEY.getCommandLineSafeName()));
	oauthClient.withSecret(cmd.getOptionValue(SECRET_PARAM));
	oauthClient.withNonce(cmd.getOptionValue(OAuthParam.OAUTH_NONCE.getCommandLineSafeName()));
	oauthClient.withOauthVersion(cmd.getOptionValue(OAuthParam.OAUTH_VERSION.getCommandLineSafeName()));
	//encryption alg and method are ok with default
    }

    private void setupSignatureParams(final BaseRillClient client, final CommandLine cmd){
	SignatureAuthClient saClient = (SignatureAuthClient)client;
	saClient.withApiKey(cmd.getOptionValue(RillAuthParam.API_KEY.getCommandLineSafeName()));
	saClient.withSecret(cmd.getOptionValue(SECRET_PARAM));
    }

    private void setupApiParams(final BaseRillClient client, final CommandLine cmd){
	client.withMobilePhoneNumber(cmd.getOptionValue(RillApiParam.MOBILE_NUMBER.getCommandLineSafeName()));
	client.withPhoneNumber(cmd.getOptionValue(RillApiParam.PHONE_NUMBER.getCommandLineSafeName()));
	client.withEmail(cmd.getOptionValue(RillApiParam.EMAIL.getCommandLineSafeName()));
	client.withFirstName(cmd.getOptionValue(RillApiParam.FIRST_NAME.getCommandLineSafeName()));
	client.withLastName(cmd.getOptionValue(RillApiParam.LAST_NAME.getCommandLineSafeName()));
	client.withDateOfBirth(cmd.getOptionValue(RillApiParam.DOB.getCommandLineSafeName()));
	client.withVerificationTargetAcronym(cmd.getOptionValue(RillApiParam.PARTNER_ACRONYM.getCommandLineSafeName()));
    }
}

	


