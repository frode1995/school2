package com.frode.system.dao;

import com.frode.system.domain.ProjectDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectDao {
    ProjectDO get(Long prizeId);//get都是从id得对象

    List<ProjectDO> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(ProjectDO project);

    int update(ProjectDO project);

    int remove(Long projectId);

    int batchRemove(Long[] projectIds);

    @Results(value = {//根据name查询id
            @Result(property = "userId", column = "user_id", javaType = Long.class, jdbcType = JdbcType.BIGINT)
    })
    @Select("select user_id from sys_user where name = #{name}")
    Long getUserIdByName(@Param("name") String name);////中文名字得用户id 便于统计各项数据

    //SELECT * FROM Persons WHERE City='Beijing'

    List<ProjectDO> listMyProject(Map<String,Object> map, @Param("userId") Long userId);


}
