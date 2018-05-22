package com.frode.system.dao;

import com.frode.system.domain.PaperDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


import java.util.List;
import java.util.Map;

@Mapper
public interface PaperDao {
    PaperDO get(Long paperId);//get都是从id得对象

    List<PaperDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(PaperDO paper);

    int update(PaperDO paper);

    int remove(Long paperId);

    int batchRemove(Long[] paperIds);

//    Long getUserIdByName(String name);
    @Results(value = {//根据name查询id
            @Result(property = "userId", column = "user_id", javaType = Long.class, jdbcType = JdbcType.BIGINT)
    })
    @Select("select user_id from sys_user where name = #{name}")
    Long getUserIdByName(@Param("name") String name);////中文名字得用户id 便于统计各项数据


    List<PaperDO> listMyPaper(Map<String,Object> map,@Param("userId") Long userId);
//    @Select("select * from sys_paper where user_id=#{userId}")
//    List<PaperDO> listMyPaper(Map<String,Object> map,@Param("userId") Long userId);//获取个人所有的paper
    //找到一个人所有的论文


}
