package com.rill.api.refcheck;

import com.rill.api.ReferenceCheckPayload;
//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;


//@ApiModel(description = "")
public class Candidate implements ReferenceCheckPayload { 
  /**
   **/
  private Long id = null;

  //private String externalId = null;
  /**
   **/
  private String email = null;
  /**
   **/
  private String firstName = null;
  /**
   **/
  private String lastName = null;
  /**
   **/
  private String dateOfBirth = null;
  
  
    //@ApiModelProperty(required = false, value = "")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

    //public String getExternalId() {
    //return externalId;
    //}
    //public void setExternalId(String id) {
    //this.externalId = id;
    //}

  
    //@ApiModelProperty(required = true, value = "")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  
    //@ApiModelProperty(required = false, value = "")
  public String getDateOfBirth() {
    return dateOfBirth;
  }
  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Candidate {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  email: ").append(email).append("\n");
    sb.append("  firstName: ").append(firstName).append("\n");
    sb.append("  lastName: ").append(lastName).append("\n");
    sb.append("  dateOfBirth: ").append(dateOfBirth).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
