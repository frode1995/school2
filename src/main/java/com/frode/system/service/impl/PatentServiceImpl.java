package com.frode.system.service.impl;

import com.frode.system.dao.PatentDao;
import com.frode.system.domain.PatentDO;
import com.frode.system.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PatentServiceImpl implements PatentService {

    @Autowired
    private PatentDao patentMapper;

    @Override
    public PatentDO get(Long patentId) {
        return patentMapper.get(patentId);
    }

    @Override
    public List<PatentDO> list(Map<String, Object> map) {

    /*    List<PatentDO> modelList= patentMapper.list(map);
        for (PatentDO patentDO:modelList){
            if("0".equals(patentDO.getPatentType())){
                patentDO.setPatentType("发明专利");
            }
            if("1".equals(patentDO.getPatentType())){
                patentDO.setPatentType("著作权");
            }
        }
        return modelList;*/
    return patentMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return patentMapper.count(map);
    }

    @Override
    public int save(PatentDO patent) {
        patent.setPatentNew(new Date());
        patent.setUserId(patentMapper.getUserIdByName(patent.getPatentAut()));//作者转id
        return patentMapper.save(patent);
    }

    @Override
    public int update(PatentDO patent) {
        patent.setPatentNew(new Date());
        patent.setUserId(patentMapper.getUserIdByName(patent.getPatentAut()));//作者转id
        return patentMapper.update(patent);
    }

    @Override
    public int remove(Long patentId) {
        return patentMapper.remove(patentId);
    }

    @Override
    public int batchRemove(Long[] patentIds) {
        return patentMapper.batchRemove(patentIds);
    }

    @Override
    public List<PatentDO> listMyPatent(Map<String, Object> map, Long userId) {
        return patentMapper.listMyPatent(map, userId);
    }
}
