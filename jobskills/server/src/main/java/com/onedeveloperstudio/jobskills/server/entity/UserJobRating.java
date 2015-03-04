package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User: y.zakharov
 * Date: 27.02.15
 */
@Table(name = "userjobrating")
@Entity(name="userjobrating")
public class UserJobRating  extends BaseEntity{
  private JobEntity job;
  private Integer rating;
  private SysUserEntity user;

  @ManyToOne(optional = false)
  public JobEntity getJob() {
    return job;
  }

  public void setJob(JobEntity job) {
    this.job = job;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  @ManyToOne(optional = false)
  public SysUserEntity getUser() {
    return user;
  }

  public void setUser(SysUserEntity user) {
    this.user = user;
  }
}
