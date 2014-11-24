package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.server.service.BaseCommentaryService;
import com.onedeveloperstudio.core.server.service.BaseVoteServiceImlp;
import com.onedeveloperstudio.jobskills.common.dto.WayToImproveSkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: User
 * Date: 20.09.14
 */

@Service
public class WayToImproveSkillServiceImpl extends BaseVoteServiceImlp<WayToImproveSkillDto> implements WayToImproveSkillService {

  @Transactional
  @Override
  public void comment(Long id, CommentaryDto comment) {
    WayToImproveSkillDto wtis = this.load(id);
    wtis.getCommentaries().add(comment);
    this.save(wtis);
  }
}
