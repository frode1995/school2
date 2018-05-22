package com.frode.system.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InfoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    //
    private Long infoId;
    //标题
    private String title;
    //创建人id
    private Long created;
    //最近修改人id
    private Long modified;
    //内容
    private String content;
    //类型
    private String type;
    //标签
    private String tags;
    //分类
    private String categories;
    //状态
    private Integer status;
    //作者  谁最后改动的 并不是作者  作者是user_id
    private String author;
    //创建时间
    private Date gtmCreate;
    //修改时间
    private Date gtmModified;
}
