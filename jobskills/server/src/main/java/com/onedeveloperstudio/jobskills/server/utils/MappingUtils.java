package com.onedeveloperstudio.jobskills.server.utils;

import com.onedeveloperstudio.jobskills.common.dto.JobDto;
import com.onedeveloperstudio.jobskills.common.dto.RequiredSkillDto;
import com.onedeveloperstudio.jobskills.server.entity.JobEntity;
import com.onedeveloperstudio.jobskills.server.entity.RequiredSkill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: y.zakharov
 * Date: 12.12.13
 */
public class MappingUtils extends com.onedeveloperstudio.core.server.utils.MappingUtils {
  private static Map<Long, JobDto> mappedObjects = new HashMap<>();

  public static JobDto jobToDto(JobEntity entity) {
    JobDto dto = new JobDto();
    dto.setId(entity.getId());
    dto.setCaption(entity.getCaption());
    dto.setDescription(entity.getDescription());
    mappedObjects.put(dto.getId(), dto);
    List<JobDto> children = new ArrayList<>(entity.getChildren().size());
    for (JobEntity job : entity.getChildren()) {
      children.add(jobToDto(job));
    }
    dto.setChildren(children);
    if (entity.getParent() != null) {
      dto.setParent(mappedObjects.get(entity.getParent().getId()));
    }
    return dto;
  }
}
