package com.onedeveloperstudio.jobskills.common.dto;

import com.onedeveloperstudio.core.common.dto.BaseDto;

import java.util.Date;

/**
 * User: y.zakharov
 * Date: 01.08.14
 */
public class DashBoardDto extends BaseDto {
  //private UserAccount user;
  private WayToImproveSkillDto way;
  private Integer minutes;
  private Integer hours;
  private Date date;

  public WayToImproveSkillDto getWay() {
    return way;
  }

  public void setWay(WayToImproveSkillDto way) {
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
