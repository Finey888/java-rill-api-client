package com.rill.api.refcheck;


//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class FactCheckResult  { 
  /**
   **/
  private String question = null;
  
  
    //@ApiModelProperty(required = false, value = "")
  public String getQuestion() {
    return question;
  }
  public void setQuestion(String question) {
    this.question = question;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class FactCheckResult {\n");
    
    sb.append("  question: ").append(question).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
