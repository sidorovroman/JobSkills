package com.onedeveloperstudio.core.common.dto;

public class BaseDto implements Dto {
  private Long id;

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }
}
