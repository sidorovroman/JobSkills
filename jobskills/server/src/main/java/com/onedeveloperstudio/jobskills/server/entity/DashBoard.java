package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.security.UserAccount;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * User: y.zakharov
 * Date: 25.07.14
 */
@Entity
public class DashBoard extends BaseEntity{
  private UserAccount user;
  private WayToImproveSkill way;
  private Integer minutes;
  private Integer hours;
  private Date date;

  @ManyToOne
  public UserAccount getUser() {
    return user;
  }

  public void setUser(UserAccount user) {
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
