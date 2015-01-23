package com.rill.api.refcheck;

//import com.rill.common.refcheck.SentimentResult;
import java.util.ArrayList;
import java.util.List;

//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class Sentiments  { 
  /**
   **/
  private List<SentimentResult> sentimentResults = new ArrayList<SentimentResult>() ;
  /**
   **/
  private Float overallScore = null;
  
  
    //@ApiModelProperty(required = false, value = "")
  public List<SentimentResult> getSentimentResults() {
    return sentimentResults;
  }
  public void setSentimentResults(List<SentimentResult> sentimentResults) {
    this.sentimentResults = sentimentResults;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public Float getOverallScore() {
    return overallScore;
  }
  public void setOverallScore(Float overallScore) {
    this.overallScore = overallScore;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sentiments {\n");
    
    sb.append("  sentimentResults: ").append(sentimentResults).append("\n");
    sb.append("  overallScore: ").append(overallScore).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
