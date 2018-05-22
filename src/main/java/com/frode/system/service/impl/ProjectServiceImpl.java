package com.frode.system.service.impl;

import com.frode.system.dao.ProjectDao;
import com.frode.system.domain.ProjectDO;
import com.frode.system.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectMapper;
    @Override
    public ProjectDO get(Long projectId) {
        return projectMapper.get(projectId);
    }

    @Override
    public List<ProjectDO> list(Map<String, Object> map) {
        return projectMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return projectMapper.count(map);
    }

    @Override
    public int save(ProjectDO project) {
        project.setProjectNew(new Date());
        project.setUserId(projectMapper.getUserIdByName(project.getProjectLeader()));//负责人转换为id
        return projectMapper.save(project);
    }

    @Override
    public int update(ProjectDO project) {
        project.setProjectNew(new Date());
        project.setUserId(projectMapper.getUserIdByName(project.getProjectLeader()));//负责人转换为id
        return projectMapper.update(project);
    }

    @Override
    public int remove(Long projectId) {
        return projectMapper.remove(projectId);
    }

    @Override
    public int batchRemove(Long[] projectIds) {
        return projectMapper.batchRemove(projectIds);
    }

    @Override
    public List<ProjectDO> listMyProject(Map<String, Object> map, Long userId) {
        return projectMapper.listMyProject(map,userId);
    }
}
