package com.frode.system.service;

import com.frode.system.domain.InfoDO;

import java.util.List;
import java.util.Map;

public interface InfoService {
    InfoDO get(Long infoId);

    List<InfoDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(InfoDO content);

    int update(InfoDO content);//content 或有错误

    int remove(Long infoId);

    int batchRemove(Long[] infoIds);
}
