package com.rill.api.refcheck;


//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class FractionScore  { 
  /**
   **/
  private Integer confirmedCount = null;
  /**
   **/
  private Integer totalCount = null;
  
  
    //@ApiModelProperty(required = false, value = "")
  public Integer getConfirmedCount() {
    return confirmedCount;
  }
  public void setConfirmedCount(Integer confirmedCount) {
    this.confirmedCount = confirmedCount;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public Integer getTotalCount() {
    return totalCount;
  }
  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class FractionScore {\n");
    
    sb.append("  confirmedCount: ").append(confirmedCount).append("\n");
    sb.append("  totalCount: ").append(totalCount).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
