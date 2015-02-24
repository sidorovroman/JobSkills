package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.server.service.BaseVoteServiceImlp;
import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.jobskills.common.dto.WayToImproveSkillDto;
import com.onedeveloperstudio.jobskills.server.dao.repositories.WayToImproveSkillRepository;
import com.onedeveloperstudio.jobskills.server.entity.WayToImproveSkill;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

  @Autowired
  private SysUserService sysUserService;

  @PostConstruct
  private  void init(){
    AppObjDict dict = AppObjDict.getInstance();
    AppObj appobj = dict.getAppObj("wayToImproveSkill");
    setAppObj(appobj);
  }

  @Override
  @Transactional(readOnly = true)
  public List<WayToImproveSkillDto> loadAllbySkill(Long skillId, Pageable pageable) {
    List<WayToImproveSkill> list = repository.findAllBySkillsId(skillId, pageable);
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

  @Override
  public WayToImproveSkillDto insert(WayToImproveSkillDto dto) {
    setAuthor(dto);
    return super.insert(dto);
  }

  private void setAuthor(WayToImproveSkillDto dto){
    dto.setAuthor(sysUserService.getAuthentication());
  }
}
