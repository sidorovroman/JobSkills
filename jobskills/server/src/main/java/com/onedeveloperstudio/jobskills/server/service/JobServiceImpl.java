package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.server.service.BaseServiceImpl;
import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.server.dao.repositories.JobRepository;
import com.onedeveloperstudio.jobskills.server.entity.JobEntity;
import com.onedeveloperstudio.jobskills.server.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 22.07.14
 */
@Service
public class JobServiceImpl extends BaseServiceImpl<JobDto> implements JobService {
  @Autowired
  private JobRepository repository;

  @Autowired
  private SysUserService sysUserService;

  @PostConstruct
  private  void init(){
    AppObjDict dict = AppObjDict.getInstance();
    AppObj appobj = dict.getAppObj("job");
    setAppObj(appobj);
  }

  @Override
  @Transactional(readOnly = true)
  public List<JobDto> getAllParents() {
    List<JobEntity> list =  repository.findAllByParentOrderByIdAsc(null);
    List<JobDto> result = new ArrayList<>(list.size());
    for(JobEntity job : list){
      result.add(MappingUtils.jobToDto(job));
    }
    return result;
  }

  @Override
  public Long getCountBetweenDates(Long dateFrom, Long dateTo) {
    return repository.countByAddDateBetween(dateFrom, dateTo);
  }

  @Override
  public JobDto insert(JobDto dto) {
    setAuthor(dto);
    return super.insert(dto);
  }

  private void setAuthor(JobDto dto){
    dto.setAuthor(sysUserService.getAuthentication());
  }

  @Override
  public JobDto update(JobDto dto) {
    setAuthor(dto);
    return super.update(dto);
  }
}
