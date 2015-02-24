package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.common.util.FieldSize;
import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.core.server.entity.Commentary;
import com.onedeveloperstudio.core.server.entity.Vote;
import com.onedeveloperstudio.core.server.entity.user.SysUserEntity;
import com.onedeveloperstudio.jobskills.common.GRADE;
import com.onedeveloperstudio.jobskills.common.ResourceType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 20.07.14
 */
@Table(name = "waytoimproveskill")
@Entity(name = "waytoimproveskill")
public class WayToImproveSkill extends BaseEntity {
  private GRADE grade;
  private String caption;
  private String description;
  private SysUserEntity author;
  private List<RequiredSkill> skills;
  private String link;
  private ResourceType resourceType;
  private Long addDate;
  private List<Vote> votes;
  private List<Commentary> commentaries;

  @Enumerated(EnumType.STRING)
  @Column(length = 100)
  public GRADE getGrade() {
    return grade;
  }

  public void setGrade(GRADE grade) {
    this.grade = grade;
  }

  @Column(nullable = false, length = FieldSize.CAPTION)
  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  @ManyToMany
  @JoinTable(name = "requiredskill_waytoimproveskill", joinColumns = {
      @JoinColumn(name = "ways_id", nullable = false, updatable = true)},
      inverseJoinColumns = {@JoinColumn(name = "skill_id",
          nullable = false, updatable = true)})
  public List<RequiredSkill> getSkills() {
    return skills;
  }

  public void setSkills(List<RequiredSkill> skills) {
    this.skills = skills;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Enumerated(EnumType.STRING)
  @Column(length = 100, name = "resourcetype")
  public ResourceType getResourceType() {
    return resourceType;
  }

  public void setResourceType(ResourceType resourceType) {
    this.resourceType = resourceType;
  }

  @Column(nullable = false)
  public Long getAddDate() {
    return addDate;
  }

  public void setAddDate(Long addDate) {
    this.addDate = addDate;
  }

  @Column(nullable = false, length = FieldSize.DESCRIPTION)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @ManyToMany
  @JoinTable(name = "waytoimproveskill_votes", joinColumns = {
      @JoinColumn(name = "waytoimproveskill_id", nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "vote_id",
          nullable = false, updatable = false)})
  public List<Vote> getVotes() {
    return votes;
  }

  public void setVotes(List<Vote> votes) {
    this.votes = votes;
  }

  @OneToMany
  @JoinTable(name = "waytoimproveskill_commentary", joinColumns = {
      @JoinColumn(name = "waytoimproveskill_id", nullable = false, updatable = false)},
      inverseJoinColumns = {@JoinColumn(name = "commentary_id",
          nullable = false, updatable = false)})
  public List<Commentary> getCommentaries() {
    return commentaries;
  }

  public void setCommentaries(List<Commentary> commentaries) {
    this.commentaries = commentaries;
  }

  @ManyToOne
  public SysUserEntity getAuthor() {
    return author;
  }

  public void setAuthor(SysUserEntity author) {
    this.author = author;
  }
}
