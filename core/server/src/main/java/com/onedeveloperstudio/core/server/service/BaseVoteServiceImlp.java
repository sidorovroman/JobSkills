package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.common.dto.RatedDto;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.common.dto.VoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 18.11.14
 */
public class BaseVoteServiceImlp<D extends RatedDto> extends BaseServiceImpl<D> implements VoteService {

  @Autowired
  private SysUserService sysUserService;

  @Override
  @Transactional(readOnly = true)
  public D load(Long id) {
    D obj = super.load(id);
    try{
      SysUserDto user = sysUserService.getAuthentication();
      sumsRating(obj, user);
    } catch (Exception e){

    }
    return obj;
  }

  @Override
  @Transactional(readOnly = true)
  public List<D> loadAll() {
    List<D> result = super.loadAll();
    SysUserDto user = null;
    try{
      user = sysUserService.getAuthentication();
    }catch (AuthenticationServiceException e){
      //Норма, неавторизированный пользователь
    }
    for (D obj : result) {
      sumsRating(obj, user);
    }
    return result;
  }

  @Override
  @Secured("ROLE_USER")
  public D update(D dto) {
    D obj = super.update(dto);
    SysUserDto user = sysUserService.getAuthentication();
    sumsRating(obj, user);
    return obj;
  }

  @Override
  @Secured("ROLE_USER")
  @Transactional
  public Integer vote(Long id, VoteState state) {
    D ratedObject = this.load(id);
    SysUserDto user = sysUserService.getAuthentication();
    for (VoteDto vote : ratedObject.getVotes()) {
      if (vote.getUser().equals(user)) {
        return ratedObject.getRating();
      }
    }
    VoteDto vote = new VoteDto();
    vote.setVoteDate(new Date().getTime());
    vote.setState(state);
    vote.setUser(user);
    ratedObject.getVotes().add(vote);
    ratedObject = this.update(ratedObject);
    return ratedObject.getRating();
  }

  private void sumsRating(D obj, SysUserDto user) {
    int i = 0;
    if (obj.getVotes() != null) {
      for (VoteDto vote : obj.getVotes()) {
        if (vote.getUser().equals(user)) {
          obj.setCanVote(false);
        }
        i += (vote.getState() == VoteState.UP ? 1 : -1);
      }
    }
    obj.setRating(i);
  }
}
