package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.server.service.BaseVoteServiceImpl;
import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.jobskills.common.dto.NewsDto;
import com.onedeveloperstudio.jobskills.server.dao.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * User: User
 * Date: 20.09.14
 */
@Service
public class NewsServiceImpl extends JobSkillsVoteServiceImpl<NewsDto> implements NewsService {
  @Autowired
  private NewsRepository newsRepository;

  @Autowired
  private SysUserService sysUserService;

  @Transactional
  @Override
  public CommentaryDto comment(Long id, CommentaryDto comment) {
    NewsDto news = this.load(id);
    comment.setAuthor(sysUserService.getAuthentication());
    comment.setAddDate(new Date());
    news.getCommentaries().add(comment);
    news = this.save(news);
    return news.getCommentaries().stream()
        .filter(item -> (item.getAuthor().equals(comment.getAuthor())) && item.getAddDate().equals(comment.getAddDate())).findFirst().get();
  }

  @Override
  public Long countNewsAddedBetweenDates(Long from, Long to) {
    return newsRepository.countByAddDateBetween(from, to);
  }
}
