package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.server.service.BaseServiceImpl;
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

  @PostConstruct
  private  void init(){
    AppObjDict dict = AppObjDict.getInstance();
    AppObj appobj = dict.getAppObj("job");
    setAppObj(appobj);
  }

  @Transactional(readOnly = true)
  @Override
  public List<JobDto> loadAll() {
    List<JobDto> list = super.loadAll();
    return list;
  }

  @Override
  @Transactional(readOnly = true)
  public List<JobDto> getAllParents() {
    List<JobEntity> list =  repository.findAllByParent(null);
    List<JobDto> result = new ArrayList<>(list.size());
    for(JobEntity job : list){
      result.add(MappingUtils.jobToDto(job));
    }
    return result;
  }
}
