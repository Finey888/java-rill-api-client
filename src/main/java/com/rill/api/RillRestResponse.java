package com.rill.api;

import javax.ws.rs.core.Response;

public class RillRestResponse {

    private Response.Status status;
    private String url;
    private Object dataPayload;
    private String message;

    public RillRestResponse(){
    }
    
    public RillRestResponse(String url){
        this.url = url;
    }

    public int getStatusCode() {
        return this.status.getStatusCode();
    }

    public Response.Status getStatus() {
        return this.status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    public String getUrl(){
        return this.url;
    }

    public Object getDataPayload(){
        return dataPayload;
    }
    
    public String getMessage(){
        return message;
    }
    
    public RillRestResponse withSuccess() {
        this.status = Response.Status.OK;
        return this;
    }
    
    public RillRestResponse withSuccess(Object data) {
        this.status = Response.Status.OK;
        this.dataPayload = data;
        this.message="OK";
        return this;
    }
    
    public RillRestResponse withError(Response.Status status, String message){
        this.status = status;
        this.message = message;
        return this;
    }

    @Override
    public String toString(){
	StringBuilder sb = new StringBuilder();
	return sb
	    .append("status=["+status)
	    .append("], payload=["+dataPayload)
	    .append("], message=["+message)
	    .append("], url=["+url+"]")
	    .toString();
    }
	    
}
