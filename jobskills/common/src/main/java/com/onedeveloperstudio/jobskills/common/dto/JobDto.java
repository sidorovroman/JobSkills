package com.onedeveloperstudio.jobskills.common.dto;

import com.onedeveloperstudio.core.common.dto.BaseDto;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 17.07.14
 */
public class JobDto extends BaseDto {
  private String caption;
  private String description;
  private JobDto parent;
  private List<JobDto> children;
  private List<RequiredSkillDto> skills;

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

  public List<JobDto> getChildren() {
    return children;
  }

  public void setChildren(List<JobDto> children) {
    this.children = children;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<RequiredSkillDto> getSkills() {
    return skills;
  }

  public void setSkills(List<RequiredSkillDto> skills) {
    this.skills = skills;
  }
}
