package com.onedeveloperstudio.jobskills.server.service;

import com.onedeveloperstudio.core.common.VoteState;
import com.onedeveloperstudio.core.common.dto.CommentaryDto;
import com.onedeveloperstudio.core.common.dto.RatedDto;
import com.onedeveloperstudio.core.common.dto.SysUserDto;
import com.onedeveloperstudio.core.server.service.BaseVoteServiceImpl;
import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.jobskills.common.dto.UserJobRatingDto;
import com.onedeveloperstudio.jobskills.common.dto.WayToImproveSkillDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * User: y.zakharov
 * Date: 05.03.15
 */
public class JobSkillsVoteServiceImpl<D extends RatedDto> extends BaseVoteServiceImpl<D> {
  @Autowired
  private UserJobRatingService userJobRatingService;

  @Override
  public Integer vote(Long id, VoteState state) {
    D ratedObject = this.load(id);
    updateAuthorRating(ratedObject, state);
    return super.vote(id, state);
  }

  private void updateAuthorRating(D ratedObject, VoteState state) {
    JobDto job = null;
    SysUserDto author = null;
    if(ratedObject instanceof CommentaryDto){
      //TODO - доделать, после появления системы комментирования
    }
    if (ratedObject instanceof RequiredSkillDto) {
      RequiredSkillDto skill = (RequiredSkillDto) ratedObject;
      author = skill.getAuthor();
      job = skill.getJob();
      updateKarma(author, job, state);
    } else if (ratedObject instanceof WayToImproveSkillDto) {
      WayToImproveSkillDto wayToImproveSkillDto = (WayToImproveSkillDto) ratedObject;
      List<RequiredSkillDto> skills = wayToImproveSkillDto.getSkills();
      List<JobDto> jobs = new ArrayList<>();
      for (RequiredSkillDto skill : skills) {
        jobs.add(skill.getJob());
      }
      for (JobDto currentJob : jobs) {
        updateKarma(author, currentJob, state);
      }
    }
  }

  private void updateKarma(SysUserDto author, JobDto job, VoteState state) {
    UserJobRatingDto userJobRatingDto = userJobRatingService.findByUserAndJob(author, job);
    if (userJobRatingDto != null) {
      userJobRatingDto.setRating(userJobRatingDto.getRating() + (state == VoteState.DOWN ? -1 : 1));
      userJobRatingService.update(userJobRatingDto);
    } else {
      userJobRatingDto = new UserJobRatingDto();
      userJobRatingDto.setJob(job);
      userJobRatingDto.setUser(author);
      userJobRatingDto.setRating(1);
      userJobRatingService.insert(userJobRatingDto);
    }
  }
}
