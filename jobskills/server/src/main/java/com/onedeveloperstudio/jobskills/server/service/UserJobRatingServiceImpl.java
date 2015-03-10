package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.appobj.AppObj;
import com.onedeveloperstudio.core.common.appobj.AppObjDict;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.service.BaseServiceImpl;
import com.onedeveloperstudio.core.server.service.SysUserService;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.common.dto.UserJobRatingDto;
import com.onedeveloperstudio.jobskills.server.dao.repositories.UserJobRatingRepository;
import com.onedeveloperstudio.jobskills.server.entity.UserJobRating;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * User: y.zakharov
 * Date: 05.03.15
 */
@Service
public class UserJobRatingServiceImpl extends BaseServiceImpl<UserJobRatingDto> implements UserJobRatingService {

  @Autowired
  private UserJobRatingRepository repository;

  @Autowired
  private Mapper mapper;

  @PostConstruct
  private void init(){
    AppObjDict dict = AppObjDict.getInstance();
    AppObj appobj = dict.getAppObj("userJobRating");
    setAppObj(appobj);
  }

  @Override
  public UserJobRatingDto findByUserAndJob(SysUserDto user, JobDto job) {
    UserJobRating ratingEntity = repository.findOneByUserIdAndJobId(user.getId(), job.getId());
    if (ratingEntity == null) {
      return null;
    }
    return mapper.map(ratingEntity, UserJobRatingDto.class);
  }
}
