package com.frode.system.dao;


import com.frode.system.domain.InfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface InfoDao {
    //各项信息的mapper
    InfoDO get(Long infoId);

    List<InfoDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(InfoDO content);

    int update(InfoDO content);

    int remove(Long infoId);

    int batchRemove(Long[] infoIds);
}

