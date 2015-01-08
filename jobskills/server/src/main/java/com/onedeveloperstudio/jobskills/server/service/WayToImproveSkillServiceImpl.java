package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.server.service.BaseCommentaryService;
import com.onedeveloperstudio.core.server.service.BaseVoteServiceImlp;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.jobskills.common.dto.WayToImproveSkillDto;
import com.onedeveloperstudio.jobskills.server.dao.repositories.RequiredSkillRepository;
import com.onedeveloperstudio.jobskills.server.dao.repositories.WayToImproveSkillRepository;
import com.onedeveloperstudio.jobskills.server.entity.RequiredSkill;
import com.onedeveloperstudio.jobskills.server.entity.WayToImproveSkill;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * User: User
 * Date: 20.09.14
 */

@Service
public class WayToImproveSkillServiceImpl extends BaseVoteServiceImlp<WayToImproveSkillDto> implements WayToImproveSkillService {

  @Autowired
  private WayToImproveSkillRepository repository;
  @Autowired
  private Mapper mapper;

  @Override
  @Transactional(readOnly = true)
  public List<WayToImproveSkillDto> loadAllbySkill(Long jobId) {
    List<WayToImproveSkill> list = repository.findAllBySkillId(jobId);
    List<WayToImproveSkillDto> result = new ArrayList<>(list.size());
    for(WayToImproveSkill skill : list){
      result.add(mapper.map(skill, WayToImproveSkillDto.class));
    }
    return result;
  }

  @Transactional
  @Override
  public void comment(Long id, CommentaryDto comment) {
    WayToImproveSkillDto wtis = this.load(id);
    wtis.getCommentaries().add(comment);
    this.save(wtis);
  }
}
