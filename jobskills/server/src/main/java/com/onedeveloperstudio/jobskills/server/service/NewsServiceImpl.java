package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.server.service.BaseCommentaryService;
import com.onedeveloperstudio.core.server.service.BaseVoteServiceImlp;
import com.onedeveloperstudio.jobskills.common.dto.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: User
 * Date: 20.09.14
 */
@Service
public class NewsServiceImpl extends BaseVoteServiceImlp<NewsDto> implements NewsService {

  @Transactional
  @Override
  public void comment(Long id, CommentaryDto comment) {
    NewsDto news = this.load(id);
    news.getCommentaries().add(comment);
    this.save(news);
  }
}
