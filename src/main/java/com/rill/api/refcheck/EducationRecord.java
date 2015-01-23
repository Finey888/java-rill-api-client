package com.rill.api.refcheck;


//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class EducationRecord  { 
  /**
   **/
  private String school = null;
  /**
   **/
  private String degree = null;
  /**
   **/
  private String schoolCheckStatus = null;
  /**
   **/
  private String degreeCheckStatus = null;
  
  
    //@ApiModelProperty(required = false, value = "")
  public String getSchool() {
    return school;
  }
  public void setSchool(String school) {
    this.school = school;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public String getDegree() {
    return degree;
  }
  public void setDegree(String degree) {
    this.degree = degree;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public String getSchoolCheckStatus() {
    return schoolCheckStatus;
  }
  public void setSchoolCheckStatus(String schoolCheckStatus) {
    this.schoolCheckStatus = schoolCheckStatus;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public String getDegreeCheckStatus() {
    return degreeCheckStatus;
  }
  public void setDegreeCheckStatus(String degreeCheckStatus) {
    this.degreeCheckStatus = degreeCheckStatus;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class EducationRecord {\n");
    
    sb.append("  school: ").append(school).append("\n");
    sb.append("  degree: ").append(degree).append("\n");
    sb.append("  schoolCheckStatus: ").append(schoolCheckStatus).append("\n");
    sb.append("  degreeCheckStatus: ").append(degreeCheckStatus).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
