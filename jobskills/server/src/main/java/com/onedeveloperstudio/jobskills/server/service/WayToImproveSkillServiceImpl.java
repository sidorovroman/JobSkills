package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
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

import javax.annotation.PostConstruct;
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

  @PostConstruct
  private  void init(){
    AppObjDict dict = AppObjDict.getInstance();
    AppObj appobj = dict.getAppObj("wayToImproveSkill");
    setAppObj(appobj);
  }

  @Override
  @Transactional(readOnly = true)
  public List<WayToImproveSkillDto> loadAllbySkill(Long skillId) {
    List<WayToImproveSkill> list = repository.findAllBySkillId(skillId);
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
