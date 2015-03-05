package com.onedeveloperstudio.jobskills.common.dto;

import com.onedeveloperstudio.core.common.dto.BaseDto;
import com.onedeveloperstudio.core.common.dto.SysUserDto;

/**
 * User: y.zakharov
 * Date: 05.03.15
 */
public class UserJobRatingDto extends BaseDto {
  private SysUserDto user;
  private JobDto job;
  private Integer rating;

  public SysUserDto getUser() {
    return user;
  }

  public void setUser(SysUserDto user) {
    this.user = user;
  }

  public JobDto getJob() {
    return job;
  }

  public void setJob(JobDto job) {
    this.job = job;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }
}
