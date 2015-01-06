package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * User: y.zakharov
 * Date: 25.07.14
 */
@Table(name = "dashboard")
@Entity(name="dashboard")
public class DashBoard extends BaseEntity{
  private SysUserEntity user;
  private WayToImproveSkill way;
  private Integer minutes;
  private Integer hours;
  private Long date;

  @ManyToOne
  public SysUserEntity getUser() {
    return user;
  }

  public void setUser(SysUserEntity user) {
    this.user = user;
  }

  @ManyToOne
  public WayToImproveSkill getWay() {
    return way;
  }

  public void setWay(WayToImproveSkill way) {
    this.way = way;
  }

  public Integer getMinutes() {
    return minutes;
  }

  public void setMinutes(Integer minutes) {
    this.minutes = minutes;
  }

  public Integer getHours() {
    return hours;
  }

  public void setHours(Integer hours) {
    this.hours = hours;
  }

  public Long getDate() {
    return date;
  }

  public void setDate(Long date) {
    this.date = date;
  }
}
