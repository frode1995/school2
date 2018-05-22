package com.frode.system.domain;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PatentDO implements Serializable{//专利
    private static final long serialVersionUID = 1L;

    private Long patentId;//专利编号
    private String patentName;
    private String patentType;//专利类型
    private String patentAut;//专利作者
    private String ratio;//所占比例
    private String patentNum;//专利编号

    private Long    patentImgId;
    private Long    patentFileId;
    private Date patentTime;
    private Date patentNew;
    private String patentInfo;

    private Long userId;



}
