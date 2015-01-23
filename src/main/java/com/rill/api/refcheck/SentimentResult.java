package com.rill.api.refcheck;


//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class SentimentResult  { 
  /**
   **/
  private String sentimentString = null;
  /**
   **/
  private Float sentimentScore = null;
  
  
    //@ApiModelProperty(required = false, value = "")
  public String getSentimentString() {
    return sentimentString;
  }
  public void setSentimentString(String sentimentString) {
    this.sentimentString = sentimentString;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public Float getSentimentScore() {
    return sentimentScore;
  }
  public void setSentimentScore(Float sentimentScore) {
    this.sentimentScore = sentimentScore;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class SentimentResult {\n");
    
    sb.append("  sentimentString: ").append(sentimentString).append("\n");
    sb.append("  sentimentScore: ").append(sentimentScore).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
