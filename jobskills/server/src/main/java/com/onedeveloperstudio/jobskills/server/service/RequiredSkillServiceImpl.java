package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.service.BaseCommentaryService;
import com.onedeveloperstudio.core.server.service.BaseVoteServiceImlp;
import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.jobskills.common.dto.WayToImproveSkillDto;
import com.onedeveloperstudio.jobskills.server.dao.repositories.RequiredSkillRepository;
import com.onedeveloperstudio.jobskills.server.entity.RequiredSkill;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 25.08.14
 */
@Service
public class RequiredSkillServiceImpl extends BaseVoteServiceImlp<RequiredSkillDto> implements RequiredSkillService {
  @Autowired
  private RequiredSkillRepository repository;
  @Autowired
  private Mapper mapper;

  @Autowired
  private SysUserService sysUserService;

  @PostConstruct
  private  void init(){
    AppObjDict dict = AppObjDict.getInstance();
    AppObj appobj = dict.getAppObj("requiredSkill");
    setAppObj(appobj);
  }

  @Override
  @Transactional(readOnly = true)
  public List<RequiredSkillDto> loadAllbyJob(Long jobId) {
    List<RequiredSkill> list = repository.findAllByJobId(jobId);
    List<RequiredSkillDto> result = new ArrayList<>(list.size());
    for(RequiredSkill skill : list){
      result.add(mapper.map(skill, RequiredSkillDto.class));
    }
    return result;
  }


  @Transactional
  @Override
  public void comment(Long id, CommentaryDto comment) {
    RequiredSkillDto wtis = this.load(id);
    wtis.getComments().add(comment);
    this.save(wtis);
  }

  @Override
  public RequiredSkillDto insert(RequiredSkillDto dto) {
    setAuthor(dto);
    return super.insert(dto);
  }

  private void setAuthor(RequiredSkillDto dto){
    dto.setAuthor(sysUserService.getAuthentication());
  }
}
