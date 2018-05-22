package com.frode.system.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PaperDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long paperId;//论文编号
    private String paperName;
    private String  paperJournal;//期刊/会议
    private String paperAuthor;
    private String  paperComAuthor;
    private String  paperSign;
    private String  firAutDept;
    private String myDept;
    private Long    paperImgId;
    private Long    paperFileId;
    private Date publishTime;
    private Date paperNew;
    private String isJournal;
    private String  sci;
    private String  eij;
    private String  eim;
    private String  cscd;
    private String  cnCore;
    private String  cpci;
    private String  ssci;
    private String  cssci;
    private String  ahci;
    private String  sciNum;
    private String  eiNum;
    private String  issnNum;
    private String paperInfo;//text格式
    private Long userId;
//    private String pre;//为了预览利用数据字典加的类型
}
