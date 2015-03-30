package com.rill.api.refcheck;

import com.rill.api.ReferenceCheckPayload;
//import com.rill.common.refcheck.Referral;
import java.util.ArrayList;
import java.util.List;

//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class ReferenceCheck implements ReferenceCheckPayload, ReferenceCheckRequest { 
    /**
     **/
    private Long id = null;

    private Candidate candidate;
    /**
     **/
    private Employer employer = null;
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
    public Candidate getCandidate() {
        return candidate;
    }
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

  
    //@ApiModelProperty(required = true, value = "")
    public Employer getEmployer() {
        return employer;
    }
    public void setEmployer(Employer employer) {
        this.employer = employer;
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
        sb.append("  candidate: ").append(candidate).append("\n");
        sb.append("  employer: ").append(employer).append("\n");
        sb.append("  references: ").append(references).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
