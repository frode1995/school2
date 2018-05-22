package com.frode.system.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NoticeDO implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long noticeId;//通知编号
    private String noticeText;
    private String noticeAuthor;
    private Long    noticeImgId;
    private Long    noticeFileId;
    private Date noticeReleaseTime;
    private Date noticeModifyTime;

}
