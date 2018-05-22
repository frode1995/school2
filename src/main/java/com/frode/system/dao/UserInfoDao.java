package com.frode.system.dao;


import com.frode.system.domain.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInfoDao {
    //信息与教职工对应关系
    UserInfoDO get(Long id);

    List<UserInfoDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserInfoDO userRole);

    int update(UserInfoDO userRole);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Long> listInfoId(Long userId);

    int removeByUserId(Long userId);

    int removeByInfoId(Long infoId);

    int batchSave(List<UserInfoDO> list);

    int batchRemoveByUserId(Long[] ids);
}
