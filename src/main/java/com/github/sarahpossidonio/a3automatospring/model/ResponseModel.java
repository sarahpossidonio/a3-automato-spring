package com.github.sarahpossidonio.a3automatospring.model;

public class ResponseModel {
    
    private String response;

    public ResponseModel(String response){
        this.response = response;
    }

    public ResponseModel(){
    }
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
