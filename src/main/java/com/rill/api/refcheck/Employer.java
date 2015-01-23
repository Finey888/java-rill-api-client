package com.rill.api.refcheck;

import com.rill.api.ReferenceCheckPayload;

//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class Employer implements ReferenceCheckPayload { 
  /**
   **/
  private Long id = null;
  /**
   **/
  private String domain = null;
  
  
    //@ApiModelProperty(required = false, value = "")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  
    //@ApiModelProperty(required = true, value = "")
  public String getDomain() {
    return domain;
  }
  public void setDomain(String domain) {
    this.domain = domain;
  }

  
  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Employer {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  domain: ").append(domain).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
