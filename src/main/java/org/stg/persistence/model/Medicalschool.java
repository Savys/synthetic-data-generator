package org.stg.persistence.model;

public class Medicalschool extends BaseModel
{
  private int id;
  private String name;
  private String certification;

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

  public String getCertification()
  {
    return this.certification;
  }

  public void setCertification(String certification)
  {
    this.certification = certification;
  }

  public String toString()
  {
    StringBuffer buffer = new StringBuffer("com.datagenerator.pojo").append(".").append("Medicalschool")
                                                                    .append("(");

    buffer.append("[").append("id").append("=").append(id).append("]");
    buffer.append("[").append("name").append("=").append(name).append("]");
    buffer.append("[").append("certification").append("=").append(certification).append("]");

    return buffer.append(")").toString();
  }
}
