package com.onedeveloperstudio.jobskills.common.dto;

import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.common.dto.RatedDto;
import com.onedeveloperstudio.core.common.dto.SysUserDto;

import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 02.09.14
 */
public class NewsDto extends RatedDto {
  private String caption;
  private String body;
  private Long addDate;
  private SysUserDto author;
  private String link;
  private String tags;
  private List<CommentaryDto> commentaries;
  private int rating;

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

  public Long getAddDate() {
    return addDate;
  }

  public void setAddDate(Long addDate) {
    this.addDate = addDate;
  }

  public SysUserDto getAuthor() {
    return author;
  }

  public void setAuthor(SysUserDto author) {
    this.author = author;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
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

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }
}
