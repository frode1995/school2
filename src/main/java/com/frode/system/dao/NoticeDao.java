package com.frode.system.dao;

import com.frode.system.domain.NoticeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeDao {
    NoticeDO get(Long prizeId);//get都是从id得对象

    List<NoticeDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(NoticeDO notice);

    int update(NoticeDO notice);

    int remove(Long noticeId);

    int batchRemove(Long[] noticeIds);

    Long[] listAllNotice();
}
