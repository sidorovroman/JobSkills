package com.onedeveloperstudio.jobskills.server.entity;

import com.onedeveloperstudio.core.common.util.FieldSize;
import com.onedeveloperstudio.core.server.entity.BaseEntity;
import com.onedeveloperstudio.jobskills.common.GRADE;
import com.onedeveloperstudio.jobskills.common.ResourceType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 20.07.14
 */
@Table(name = "wayToImproveSkill")
@Entity(name="wayToImproveSkill")
public class WayToImproveSkill extends BaseEntity {
  private GRADE grade;
  private String caption;
  private List<RequiredSkill> skill;
  private String link;
  private ResourceType resourceType;

  @Enumerated(EnumType.STRING)
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
      @JoinColumn(name = "skill_id", nullable = false, updatable = false) },
      inverseJoinColumns = { @JoinColumn(name = "ways_id",
          nullable = false, updatable = false) })
  public List<RequiredSkill> getSkill() {
    return skill;
  }

  public void setSkill(List<RequiredSkill> skill) {
    this.skill = skill;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Enumerated(EnumType.STRING)
  public ResourceType getResourceType() {
    return resourceType;
  }

  public void setResourceType(ResourceType resourceType) {
    this.resourceType = resourceType;
  }
}
