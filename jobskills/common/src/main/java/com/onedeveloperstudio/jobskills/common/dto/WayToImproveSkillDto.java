package com.onedeveloperstudio.jobskills.common.dto;

import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.common.dto.RatedDto;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.common.dto.VoteDto;
import com.onedeveloperstudio.jobskills.common.GRADE;
import com.onedeveloperstudio.jobskills.common.ResourceType;

import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 01.08.14
 */
public class WayToImproveSkillDto extends RatedDto {
  private GRADE grade;
  private String caption;
  private String description;
  private SysUserDto author;
  private List<RequiredSkillDto> skills;
  private String link;
  private ResourceType resourceType;
  private Long addDate;
  private List<CommentaryDto> commentaries;

  public GRADE getGrade() {
    return grade;
  }

  public void setGrade(GRADE grade) {
    this.grade = grade;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public List<RequiredSkillDto> getSkills() {
    return skills;
  }

  public void setSkills(List<RequiredSkillDto> skills) {
    this.skills = skills;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public ResourceType getResourceType() {
    return resourceType;
  }

  public void setResourceType(ResourceType resourceType) {
    this.resourceType = resourceType;
  }

  public Long getAddDate() {
    return addDate;
  }

  public void setAddDate(Long addDate) {
    this.addDate = addDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<CommentaryDto> getCommentaries() {
    return commentaries;
  }

  public void setCommentaries(List<CommentaryDto> commentaries) {
    this.commentaries = commentaries;
  }

  public SysUserDto getAuthor() {
    return author;
  }

  public void setAuthor(SysUserDto author) {
    this.author = author;
  }
}
