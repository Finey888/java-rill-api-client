package com.rill.api;

public class DataPayload {

    public static enum Answer {
        TRUE,
        FALSE,
        UNKNOWN;
    }

    private Answer answer;
    private Object detail;

    public Answer getAnswer(){
        return answer;
    }

    public void setAnswer(Answer answer){
        this.answer = answer;
    }

    public Object getDetail(){
        return detail;
    }

    public void setDetail(Object detail){
        this.detail = detail;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        return sb
            .append("[")
            .append(answer)
            .append("; ")
            .append(detail)
            .append("]")
            .toString();
    }

}
