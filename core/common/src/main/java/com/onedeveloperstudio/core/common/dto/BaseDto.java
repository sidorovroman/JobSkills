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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || (getClass() != o.getClass())) {
      return false;
    }

    BaseDto that = (BaseDto) o;

    if (getId() == null) {
      return this == that;
    } else {
      return getId().equals(that.getId());
    }

  }

  @Override
  public int hashCode() {
    return getId() != null ? getId().hashCode() : 0;
  }
}
