package com.rill.api.refcheck;

import java.util.Date;

//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class Referral  { 

  public static enum RelationshipTypeEnum { peer, manager, client };

  /**
   **/
  private String referenceEmail = null;
  /**
   **/
  private RelationshipTypeEnum relationshipType = null;
    
  /**
   **/
  private Date lastUpdated = null;
  
  
    //@ApiModelProperty(required = true, value = "")
  public String getReferenceEmail() {
    return referenceEmail;
  }
  public void setReferenceEmail(String referenceEmail) {
    this.referenceEmail = referenceEmail;
  }

  
    //@ApiModelProperty(required = true, value = "")
  public RelationshipTypeEnum getRelationshipType() {
    return relationshipType;
  }
  public void setRelationshipType(RelationshipTypeEnum relationshipType) {
    this.relationshipType = relationshipType;
  }

    //@ApiModelProperty(required = false, value = "")
  public Date getLastUpdated() {
    return lastUpdated;
  }
  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Referral {\n");
    
    sb.append("  referenceEmail: ").append(referenceEmail).append("\n");
    sb.append("  relationshipType: ").append(relationshipType).append("\n");
    //sb.append("  status: ").append(status).append("\n");
    sb.append("  lastUpdated: ").append(lastUpdated).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
