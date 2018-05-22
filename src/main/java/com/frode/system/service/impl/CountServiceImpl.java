package com.frode.system.service.impl;

import com.frode.common.utils.graph.BarGraph;
import com.frode.system.dao.CountInfo;
import com.frode.system.dao.GuanliDao;
import com.frode.system.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CountServiceImpl implements CountService {

    @Autowired
    CountInfo countMapper;

    @Autowired
    GuanliDao guanliMapper;


    @Override
    public int sePaper() {
        return countMapper.sePaper();
    }

    @Override
    public int seProject() {
        return countMapper.seProject();
    }

    @Override
    public int sePrize() {
        return countMapper.sePrize();
    }

    @Override
    public int sePatent() {
        return countMapper.sePatent();
    }

    @Override
    public int csPaper() {
        return countMapper.csPaper();
    }

    @Override
    public int csProject() {
        return countMapper.csProject();
    }

    @Override
    public int csPrize() {
        return countMapper.csPrize();
    }

    @Override
    public int csPatent() {
        return countMapper.csPatent();
    }

    @Override
    public Map<String, String> getImgUrl(int deptId) {
        Map<String,String> map=new HashMap<>(16);
        if (deptId==6){//就代表点击了计算机学院  的两个教研室  一会加其他图
//            BarGraph barGraph=new BarGraph("CSSE");//生成图片
           String path=BarGraph.getBarchart(csPaper(),csProject(),csPrize(),csPatent(),sePaper(),seProject(),sePrize(),sePatent());
           String relatePath="../../img/table/barchart.jpg";//相对路径
           map.put("college",relatePath);//学院好一点  英文好一点
            map.put("status","success");
            map.put("deptId","6");

        }
        else if(deptId==13){//管理学院
            String path=BarGraph.getBarchart2(guanliMapper.tmPaper(),guanliMapper.tmProject(),guanliMapper.tmPrize(),guanliMapper.tmPatent(),guanliMapper.fmPaper(),guanliMapper.fmProject(),guanliMapper.fmPrize(),guanliMapper.fmPatent());
            String relatePath="../../img/table/guanlichart.jpg";//相对路径
            map.put("college",relatePath);//学院好一点  英文好一点
            map.put("status","success");
            map.put("deptId","13");
        }
        else{
            map.put("status","false");
        }
        return map;
    }
}
