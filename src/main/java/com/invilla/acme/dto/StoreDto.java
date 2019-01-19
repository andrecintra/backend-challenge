package com.invilla.acme.dto;

import com.invillia.acme.models.Store;

public class StoreDto {

  private String name;
  private String address;

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Store toEntity() {
    Store store = new Store();
    store.setName(this.getName());
    store.setAddress(this.getAddress());

    return store;
  }
}
