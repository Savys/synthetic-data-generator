package com.salesforce.industries.persistence.model;


public class Address extends BaseModel
{
  private int id;
  private Integer housenumberstart;
  private Integer housenumberend;
  private String street;
  private String unit;
  private String city;
  private String postalcode;
  private String state;
  private String country;

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

  public Integer getHousenumberstart()
  {
    return this.housenumberstart;
  }

  public void setHousenumberstart(Integer housenumberstart)
  {
    this.housenumberstart = housenumberstart;
  }

  public Integer getHousenumberend()
  {
    return this.housenumberend;
  }

  public void setHousenumberend(Integer housenumberend)
  {
    this.housenumberend = housenumberend;
  }

  public String getStreet()
  {
    return this.street;
  }

  public void setStreet(String street)
  {
    this.street = street;
  }

  public String getUnit()
  {
    return this.unit;
  }

  public void setUnit(String unit)
  {
    this.unit = unit;
  }

  public String getCity()
  {
    return this.city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getPostalcode()
  {
    return this.postalcode;
  }

  public void setPostalcode(String postalcode)
  {
    this.postalcode = postalcode;
  }

  public String getState()
  {
    return this.state;
  }

  public void setState(String state)
  {
    this.state = state;
  }

  public String getCountry()
  {
    return this.country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public String toString()
  {
    StringBuffer buffer = new StringBuffer("com.datagenerator.model").append(".").append("Address").append("(");

    buffer.append("[").append("id").append("=").append(id).append("]");
    buffer.append("[").append("housenumberstart").append("=").append(housenumberstart).append("]");
    buffer.append("[").append("housenumberend").append("=").append(housenumberend).append("]");
    buffer.append("[").append("street").append("=").append(street).append("]");
    buffer.append("[").append("unit").append("=").append(unit).append("]");
    buffer.append("[").append("city").append("=").append(city).append("]");
    buffer.append("[").append("postalcode").append("=").append(postalcode).append("]");
    buffer.append("[").append("state").append("=").append(state).append("]");
    buffer.append("[").append("country").append("=").append(country).append("]");

    return buffer.append(")").toString();
  }
}
