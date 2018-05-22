package com.frode.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GuanliDao {

    @Select("select count(sys_paper.user_id) from sys_paper where user_id in (select user_id from sys_user where sys_user.dept_id = 14)")
    int tmPaper();

    @Select("select count(sys_project.user_id) from sys_project where user_id in (select user_id from sys_user where sys_user.dept_id = 14)")
    int tmProject();

    @Select("select count(sys_prize.user_id) from sys_prize where user_id in (select user_id from sys_user where sys_user.dept_id = 14)")
    int tmPrize();

    @Select("select count(sys_patent.user_id) from sys_patent where user_id in (select user_id from sys_user where sys_user.dept_id = 14)")
    int tmPatent();

    @Select("select count(sys_paper.user_id) from sys_paper where user_id in (select user_id from sys_user where sys_user.dept_id = 15)")
    int fmPaper();

    @Select("select count(sys_project.user_id) from sys_project where user_id in (select user_id from sys_user where sys_user.dept_id = 15)")
    int fmProject();

    @Select("select count(sys_prize.user_id) from sys_prize where user_id in (select user_id from sys_user where sys_user.dept_id = 15)")
    int fmPrize();

    @Select("select count(sys_patent.user_id) from sys_patent where user_id in (select user_id from sys_user where sys_user.dept_id = 15)")
    int fmPatent();
}
