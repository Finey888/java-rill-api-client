package com.rill.api.refcheck;

import com.rill.api.ReferenceCheckPayload;
//import com.rill.common.refcheck.Referral;
import java.util.ArrayList;
import java.util.List;

//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class ReferenceCheck implements ReferenceCheckPayload { 
  /**
   **/
  private Long id = null;
  /**
   **/
  private Long candidateId = null;
  /**
   **/
  private Long employerId = null;
  /**
   **/
  private List<Referral> references = new ArrayList<Referral>() ;
  
  
    //@ApiModelProperty(required = false, value = "")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  
    //@ApiModelProperty(required = true, value = "")
  public Long getCandidateId() {
    return candidateId;
  }
  public void setCandidateId(Long candidateId) {
    this.candidateId = candidateId;
  }

  
    //@ApiModelProperty(required = true, value = "")
  public Long getEmployerId() {
    return employerId;
  }
  public void setEmployerId(Long employerId) {
    this.employerId = employerId;
  }

  
    //@ApiModelProperty(required = true, value = "")
  public List<Referral> getReferences() {
    return references;
  }
  public void setReferences(List<Referral> references) {
    this.references = references;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReferenceCheck {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  candidateId: ").append(candidateId).append("\n");
    sb.append("  employerId: ").append(employerId).append("\n");
    sb.append("  references: ").append(references).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
