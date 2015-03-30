package com.rill.api.refcheck;

import com.rill.api.ReferenceCheckPayload;

//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class Employer implements ReferenceCheckPayload { 
    /**
     **/
    private Long id = null;

    //private String externalId;

    /**
     **/
    private String ein = null;
  
  
    //@ApiModelProperty(required = false, value = "")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //public String getExternalId() {
    //    return externalId;
    //}
    //public void setExternalId(String id){
    //    this.externalId = id;
    //}


    //@ApiModelProperty(required = true, value = "")
    public String getEin() {
        return ein;
    }
    public void setEin(String ein) {
        this.ein = ein;
    }

  
    @Override
    public String toString()  {
        StringBuilder sb = new StringBuilder();
        sb.append("class Employer {\n");
    
        sb.append("  id: ").append(id).append("\n");
        sb.append("  ein: ").append(ein).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
