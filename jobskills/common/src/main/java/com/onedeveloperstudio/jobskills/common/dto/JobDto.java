package com.onedeveloperstudio.jobskills.common.dto;

import com.onedeveloperstudio.core.common.dto.BaseDto;

import java.util.Set;

/**
 * User: y.zakharov
 * Date: 17.07.14
 */
public class JobDto extends BaseDto {
  private String caption;
  private JobDto parent;
  private Set<JobDto> children;

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public JobDto getParent() {
    return parent;
  }

  public void setParent(JobDto parent) {
    this.parent = parent;
  }

  public Set<JobDto> getChildren() {
    return children;
  }

  public void setChildren(Set<JobDto> children) {
    this.children = children;
  }
}
