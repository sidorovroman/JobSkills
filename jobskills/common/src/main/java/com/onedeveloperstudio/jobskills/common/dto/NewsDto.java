package com.onedeveloperstudio.jobskills.common.dto;

import com.onedeveloperstudio.core.common.dto.BaseDto;
import com.onedeveloperstudio.core.common.dto.SysUserDto;

import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 02.09.14
 */
public class NewsDto extends BaseDto {
  private String caption;
  private String body;
  private Date addDate;
  private SysUserDto author;
  private String source;
  private String tags;
  private List<CommentaryDto> commentaries;
  private List<VoteDto> votes;

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Date getAddDate() {
    return addDate;
  }

  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }

  public SysUserDto getAuthor() {
    return author;
  }

  public void setAuthor(SysUserDto author) {
    this.author = author;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public List<CommentaryDto> getCommentaries() {
    return commentaries;
  }

  public void setCommentaries(List<CommentaryDto> commentaries) {
    this.commentaries = commentaries;
  }

  public List<VoteDto> getVotes() {
    return votes;
  }

  public void setVotes(List<VoteDto> votes) {
    this.votes = votes;
  }
}
