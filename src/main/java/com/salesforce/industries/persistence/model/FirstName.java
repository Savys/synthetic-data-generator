package com.salesforce.industries.persistence.model;

public class FirstName extends BaseModel
{
  private int id;
  private String name;
  private String gender;

  //
  // getters / setters
  //
  public int getId()
  {
    return this.id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getGender()
  {
    return this.gender;
  }

  public void setGender(String gender)
  {
    this.gender = gender;
  }

  public String toString()
  {
    StringBuffer buffer = new StringBuffer("com.mycompany.pojo").append(".").append("Firstnames").append("(");

    buffer.append("[").append("id").append("=").append(id).append("]");
    buffer.append("[").append("name").append("=").append(name).append("]");
    buffer.append("[").append("gender").append("=").append(gender).append("]");

    return buffer.append(")").toString();
  }
}