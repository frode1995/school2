package com.frode.system.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProjectDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long projectId;//项目id
    private String projectName;
    private String projectLeader;//项目负责人
    private String projectInfo;//数据库为text 类型也是String
    private Long    projectImgId;
    private Long    projectFileId;
    private Date projectStart;//项目开始时间
    private Date projectEnd;//项目结束时间
    private String projectNum;//项目编号  英文加数字
    private String projectFunds;//项目资金  可以随便写
    private Date projectNew;//消息最新 更改时间  取系统最新时间
    private Long userId;//这种存的方式 比存userId和projectId paperId prizeId patentId  省事一点
    //用leader为userName 转换出来的userId


}
