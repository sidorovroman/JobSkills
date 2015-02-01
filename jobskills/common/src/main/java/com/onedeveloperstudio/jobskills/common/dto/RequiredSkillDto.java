package com.onedeveloperstudio.jobskills.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.common.dto.RatedDto;
import com.onedeveloperstudio.core.common.dto.VoteDto;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 01.08.14
 */
public class RequiredSkillDto extends RatedDto {
  @JsonIgnore
  private JobDto job;
  private String caption;
  private String description;
  @JsonIgnore
  private List<WayToImproveSkillDto> ways;
  private List<CommentaryDto> comments;

  @JsonIgnore
  public JobDto getJob() {
    return job;
  }

  @JsonProperty("job")
  public void setJob(JobDto job) {
    this.job = job;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  @JsonIgnore
  public List<WayToImproveSkillDto> getWays() {
    return ways;
  }

  @JsonProperty("ways")
  public void setWays(List<WayToImproveSkillDto> ways) {
    this.ways = ways;
  }

  public List<CommentaryDto> getComments() {
    return comments;
  }

  public void setComments(List<CommentaryDto> comments) {
    this.comments = comments;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
