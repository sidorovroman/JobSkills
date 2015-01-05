package com.onedeveloperstudio.core.server.service;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.common.dto.RatedDto;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.common.dto.User;
import com.onedeveloperstudio.core.common.dto.VoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    sumsRating(obj);
    return obj;
  }

  @Override
  @Transactional(readOnly = true)
  public List<D> loadAll() {
    List<D> result =  super.loadAll();
    for(D obj : result){
      sumsRating(obj);
    }
    return result;
  }

  @Override
  @Transactional
  public void vote(Long id, VoteState state) {
    D ratedObject = this.load(id);
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User regUser = (User) auth.getPrincipal();
    SysUserDto user = sysUserService.loadByEmail(regUser.getUsername());
    for(VoteDto vote : ratedObject.getVotes()){
      if(vote.getUser().equals(regUser)){
        return;
      }
    }
    VoteDto vote = new VoteDto();
    vote.setVoteDate(new Date());
    vote.setState(VoteState.UP);    vote.setUser(user);
    ratedObject.getVotes().add(vote);
    this.update(ratedObject);
  }

  private void sumsRating(D obj){
    int i = 0;
    for(VoteDto vote : obj.getVotes()){
      i+= (vote.getState() == VoteState.UP ? 1 : -1);
    }
    obj.setRating(i);
  }
}
