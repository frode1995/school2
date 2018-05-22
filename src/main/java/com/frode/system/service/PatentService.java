package com.frode.system.service;

import com.frode.system.domain.PatentDO;

import java.util.List;
import java.util.Map;

public interface PatentService {
    PatentDO get(Long patentId);//get都是从id得对象

    List<PatentDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(PatentDO patent);

    int update(PatentDO patent);

    int remove(Long patentId);

    int batchRemove(Long[] patentIds);

    List<PatentDO> listMyPatent(Map<String,Object> map, Long userId);
}
