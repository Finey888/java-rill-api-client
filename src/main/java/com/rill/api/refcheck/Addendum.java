package com.rill.api.refcheck;


//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class Addendum { 
  /**
   **/
  private String question = null;
  /**
   **/
  private String answer = null;
  
  
    //@ApiModelProperty(required = false, value = "")
  public String getQuestion() {
    return question;
  }
  public void setQuestion(String question) {
    this.question = question;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public String getAnswer() {
    return answer;
  }
  public void setAnswer(String answer) {
    this.answer = answer;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Addendum {\n");
    
    sb.append("  question: ").append(question).append("\n");
    sb.append("  answer: ").append(answer).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
