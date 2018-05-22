package com.frode.system.service;

import com.frode.system.domain.ProjectDO;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    ProjectDO get(Long projectId);//get都是从id得对象

    List<ProjectDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(ProjectDO project);

    int update(ProjectDO project);

    int remove(Long projectId);

    int batchRemove(Long[] projectIds);

    List<ProjectDO> listMyProject(Map<String,Object> map, Long userId);//查询个人项目
}
