package com.frode.system.controller;

import com.frode.common.controller.BaseController;
import com.frode.common.utils.R;
import com.frode.system.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CountController extends BaseController {

    @Autowired
    CountService countService;

    //点击学院后 右边图表更新
    @GetMapping("/count")
    public String countPage(Model model){
        return "system/count/users";//返回users页面
    }


    //ajax请求图片  检测到计算机学院 就显示柱状图
    @GetMapping("/count/{deptId}")//改变img src
    @ResponseBody
    public Map<String,String> barChart(@PathVariable("deptId") int deptId){
        Map<String,String> map=new HashMap<>(16);
        map=countService.getImgUrl(deptId);
        return map;
    }


}
