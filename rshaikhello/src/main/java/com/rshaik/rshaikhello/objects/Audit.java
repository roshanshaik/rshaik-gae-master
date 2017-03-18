

package com.rshaik.rshaikhello.objects;

/**
 * 
 * Domain class for Audit object. 
 * 
 * @author rshaik
 *
 */

public class Audit {

  private String json;
  private String ipAddress;
  private String createdDate;
  
  private Long id;
  

  public static final String JSON = "json";
  public static final String IP_ADDRESS = "ipAddress";
  public static final String CREATED_DATE = "createdDate";
  public static final String ID = "id";

  private Audit(Builder builder) {
    this.json = builder.json;
    this.ipAddress = builder.ipAddress;
    this.createdDate = builder.createdDate;
    this.id = builder.id;
  }

  public static class Builder {
    private String json;
    private String ipAddress;
    private String createdDate;
    
    private Long id;
    

    public Builder json(String json) {
      this.json = json;
      return this;
    }

    public Builder ipAddress(String ipAddress) {
      this.ipAddress = ipAddress;
      return this;
    }

    public Builder createdDate(String createdDate) {
      this.createdDate = createdDate;
      return this;
    }


    public Builder id(Long id) {
      this.id = id;
      return this;
    }


    public Audit build() {
      return new Audit(this);
    }
  }

  public String getJson() {
    return json;
  }

  public void setJson(String json) {
    this.json = json;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return
        "Json: " + json + ", IpAddress: " + ipAddress + ", Date: " + createdDate;
  }
}
// [END example]
