package com.frode.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CountInfo {

    //int countPaper(@Param("deptId") Long deptId);
    @Select("select count(sys_paper.user_id) from sys_paper where user_id in (select user_id from sys_user where sys_user.dept_id = 8)")
    int sePaper();

    @Select("select count(sys_project.user_id) from sys_project where user_id in (select user_id from sys_user where sys_user.dept_id = 8)")
    int seProject();

    @Select("select count(sys_prize.user_id) from sys_prize where user_id in (select user_id from sys_user where sys_user.dept_id = 8)")
    int sePrize();

    @Select("select count(sys_patent.user_id) from sys_patent where user_id in (select user_id from sys_user where sys_user.dept_id = 8)")
    int sePatent();

    @Select("select count(sys_paper.user_id) from sys_paper where user_id in (select user_id from sys_user where sys_user.dept_id = 7)")
    int csPaper();

    @Select("select count(sys_project.user_id) from sys_project where user_id in (select user_id from sys_user where sys_user.dept_id = 7)")
    int csProject();

    @Select("select count(sys_prize.user_id) from sys_prize where user_id in (select user_id from sys_user where sys_user.dept_id = 7)")
    int csPrize();

    @Select("select count(sys_patent.user_id) from sys_patent where user_id in (select user_id from sys_user where sys_user.dept_id = 7)")
    int csPatent();


}


