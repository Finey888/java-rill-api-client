package com.rill.api.refcheck;


//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class EducationStatus  { 
  /**
   **/
  private String currentlyEnrolled = null;
  /**
   **/
  private String graduated = null;
  
  
    //@ApiModelProperty(required = false, value = "")
  public String getCurrentlyEnrolled() {
    return currentlyEnrolled;
  }
  public void setCurrentlyEnrolled(String currentlyEnrolled) {
    this.currentlyEnrolled = currentlyEnrolled;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public String getGraduated() {
    return graduated;
  }
  public void setGraduated(String graduated) {
    this.graduated = graduated;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EducationStatus {\n");
    
    sb.append("  currentlyEnrolled: ").append(currentlyEnrolled).append("\n");
    sb.append("  graduated: ").append(graduated).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
