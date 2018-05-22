package com.frode.system.service.impl;

import com.frode.system.dao.InfoDao;
import com.frode.system.domain.InfoDO;
import com.frode.system.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoDao infoMapper;

    @Override
    public InfoDO get(Long infoId) {
        return infoMapper.get(infoId);
    }

    @Override
    public List<InfoDO> list(Map<String, Object> map) {
        return infoMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return infoMapper.count(map);
    }

    @Override
    public int save(InfoDO content) {
        return infoMapper.save(content);
    }

    @Override
    public int update(InfoDO content) {
        return infoMapper.update(content);
    }

    @Override
    public int remove(Long infoId) {
        return infoMapper.remove(infoId);
    }

    @Override
    public int batchRemove(Long[] infoIds) {
        return infoMapper.batchRemove(infoIds);
    }
}
