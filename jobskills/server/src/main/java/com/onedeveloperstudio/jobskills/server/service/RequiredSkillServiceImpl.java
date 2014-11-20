package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.service.BaseServiceImpl;
import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.server.service.BaseVoteServiceImlp;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.core.common.dto.VoteDto;
import com.onedeveloperstudio.jobskills.server.dao.repositories.RequiredSkillRepository;
import com.onedeveloperstudio.jobskills.server.entity.RequiredSkill;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

  @Override
  public List<RequiredSkillDto> loadAllbyJob(Long jobId) {
    List<RequiredSkill> list = repository.findAllByJobId(jobId);
    List<RequiredSkillDto> result = new ArrayList<>(list.size());
    for(RequiredSkill skill : list){
      result.add(mapper.map(skill, RequiredSkillDto.class));
    }
    return result;
  }
}
