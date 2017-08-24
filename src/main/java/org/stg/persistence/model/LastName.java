package org.stg.persistence.model;

public class LastName extends BaseModel
{
  private int id;
  private String lastname;

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

  public String getLastname()
  {
    return this.lastname;
  }

  public void setLastname(String lastname)
  {
    this.lastname = lastname;
  }
}
