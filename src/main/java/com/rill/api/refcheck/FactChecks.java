package com.rill.api.refcheck;

//import com.rill.common.refcheck.FractionScore;
//import com.rill.common.refcheck.FactCheckResult;
import java.util.ArrayList;
import java.util.List;

//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class FactChecks  { 
  /**
   **/
  private Integer totalNumberOfReferences = null;
  /**
   **/
  private Integer qualifiedNumberOfReferences = null;
  /**
   **/
  private List<FactCheckResult> factCheckResults = new ArrayList<FactCheckResult>() ;
  /**
   **/
  private FractionScore factScore = null;
  
  
    //@ApiModelProperty(required = false, value = "")
  public Integer getTotalNumberOfReferences() {
    return totalNumberOfReferences;
  }
  public void setTotalNumberOfReferences(Integer totalNumberOfReferences) {
    this.totalNumberOfReferences = totalNumberOfReferences;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public Integer getQualifiedNumberOfReferences() {
    return qualifiedNumberOfReferences;
  }
  public void setQualifiedNumberOfReferences(Integer qualifiedNumberOfReferences) {
    this.qualifiedNumberOfReferences = qualifiedNumberOfReferences;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public List<FactCheckResult> getFactCheckResults() {
    return factCheckResults;
  }
  public void setFactCheckResults(List<FactCheckResult> factCheckResults) {
    this.factCheckResults = factCheckResults;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public FractionScore getFactScore() {
    return factScore;
  }
  public void setFactScore(FractionScore factScore) {
    this.factScore = factScore;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class FactChecks {\n");
    
    sb.append("  totalNumberOfReferences: ").append(totalNumberOfReferences).append("\n");
    sb.append("  qualifiedNumberOfReferences: ").append(qualifiedNumberOfReferences).append("\n");
    sb.append("  factCheckResults: ").append(factCheckResults).append("\n");
    sb.append("  factScore: ").append(factScore).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
