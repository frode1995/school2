package com.frode.system.service;

import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface CountService {
    int sePaper();//软件

    int seProject();


    int sePrize();


    int sePatent();


    int csPaper();


    int csProject();


    int csPrize();


    int csPatent();

    Map<String,String> getImgUrl(int deptId);
}
