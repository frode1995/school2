package com.frode.system.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PrizeDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long prizeId;//奖项编号
    private String  awardee;//受奖者 转换为userId
    private Date    prizeTime;//获奖时间
    private String college;//学院
    private String dp;//学位点
    private String prizeName;//奖励名称
    private String prizeProject;//获奖项目名
    private String prizeNum;//获奖编号
    private String prizeGrade;//奖项等级
    private String  adLevel;//行政等级
    private String prizeDept;//授奖单位
    private String rank;//名次  几分之几2/12
    private Long    prizeImgId;
    private Long    prizeFileId;
    private String prizeInfo;//
    private Date prizeNew;//最后更改时间
    private Long userId;


}
