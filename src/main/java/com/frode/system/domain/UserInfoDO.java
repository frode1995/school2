package com.frode.system.domain;

import lombok.Data;

@Data
public class UserInfoDO {
    //教职工和各项信息的映射
    private Long id;
    private Long userId;
    private Long infoId;
}
