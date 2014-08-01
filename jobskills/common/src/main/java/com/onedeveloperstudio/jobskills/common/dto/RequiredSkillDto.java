package com.onedeveloperstudio.jobskills.common.dto;

import com.onedeveloperstudio.core.common.dto.BaseDto;

import java.util.List;

/**
 * User: y.zakharov
 * Date: 01.08.14
 */
public class RequiredSkillDto extends BaseDto {
  private JobDto job;
  private String caption;
  private List<WayToImproveSkillDto> ways;
  private List<CommentaryDto> comments;
  private List<VoteDto> votes;

  public JobDto getJob() {
    return job;
  }

  public void setJob(JobDto job) {
    this.job = job;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public List<WayToImproveSkillDto> getWays() {
    return ways;
  }

  public void setWays(List<WayToImproveSkillDto> ways) {
    this.ways = ways;
  }

  public List<CommentaryDto> getComments() {
    return comments;
  }

  public void setComments(List<CommentaryDto> comments) {
    this.comments = comments;
  }

  public List<VoteDto> getVotes() {
    return votes;
  }

  public void setVotes(List<VoteDto> votes) {
    this.votes = votes;
  }
}
