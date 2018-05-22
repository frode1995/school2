package com.frode.system.dao;

import com.frode.system.domain.TestDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDao {

    //表名（列） values  值   插入所有数据//成功
    @Insert("INSERT INTO sys_test(id,content) VALUES(#{id}, #{content})")
    int save(TestDO testDO);
}
