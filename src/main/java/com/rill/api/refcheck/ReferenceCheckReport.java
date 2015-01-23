package com.rill.api.refcheck;

import com.rill.api.ReferenceCheckPayload;

import java.util.ArrayList;
import java.util.List;

//import com.rill.common.refcheck.Addendum;
//import com.rill.common.refcheck.EducationRecord;
//import com.rill.common.refcheck.EducationStatus;
//import com.rill.common.refcheck.FactChecks;
//import com.rill.common.refcheck.Sentiments;

//import com.wordnik.swagger.annotations.*;


//@ApiModel(description = "")
public class ReferenceCheckReport implements ReferenceCheckPayload { 
  /**
   **/
  private Long referenceCheckId = null;
  /**
   **/
  private StatusEnum status = null;
  
  public enum StatusEnum { complete, inprogress, unknown };
  
  /**
   **/
  private List<EducationRecord> educationChecks = new ArrayList<EducationRecord>() ;
  /**
   **/
  private EducationStatus educationStatus = null;
  /**
   **/
  private FactChecks factChecks = null;
  /**
   **/
  private Sentiments sentiments = null;
  /**
   **/
  private List<Addendum> addenda = new ArrayList<Addendum>() ;
  
  
    //@ApiModelProperty(required = true, value = "")
  public Long getReferenceCheckId() {
    return referenceCheckId;
  }
  public void setReferenceCheckId(Long referenceCheckId) {
    this.referenceCheckId = referenceCheckId;
  }

  
    //@ApiModelProperty(required = true, value = "")
  public StatusEnum getStatus() {
    return status;
  }
  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public List<EducationRecord> getEducationChecks() {
    return educationChecks;
  }
  public void setEducationChecks(List<EducationRecord> educationChecks) {
    this.educationChecks = educationChecks;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public EducationStatus getEducationStatus() {
    return educationStatus;
  }
  public void setEducationStatus(EducationStatus educationStatus) {
    this.educationStatus = educationStatus;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public FactChecks getFactChecks() {
    return factChecks;
  }
  public void setFactChecks(FactChecks factChecks) {
    this.factChecks = factChecks;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public Sentiments getSentiments() {
    return sentiments;
  }
  public void setSentiments(Sentiments sentiments) {
    this.sentiments = sentiments;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public List<Addendum> getAddenda() {
    return addenda;
  }
  public void setAddenda(List<Addendum> addenda) {
    this.addenda = addenda;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReferenceCheckReport {\n");
    
    sb.append("  referenceCheckId: ").append(referenceCheckId).append("\n");
    sb.append("  status: ").append(status).append("\n");
    sb.append("  educationChecks: ").append(educationChecks).append("\n");
    sb.append("  educationStatus: ").append(educationStatus).append("\n");
    sb.append("  factChecks: ").append(factChecks).append("\n");
    sb.append("  sentiments: ").append(sentiments).append("\n");
    sb.append("  addenda: ").append(addenda).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
