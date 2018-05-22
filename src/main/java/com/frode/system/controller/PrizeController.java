package com.frode.system.controller;

import com.frode.common.controller.BaseController;
import com.frode.common.service.DictService;
import com.frode.common.utils.PageUtils;
import com.frode.common.utils.Query;
import com.frode.common.utils.R;
import com.frode.system.domain.PrizeDO;
import com.frode.system.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/prize/content")
public class PrizeController extends BaseController{

    private String prefix="system/prize";

    @Autowired
    PrizeService prizeService;

    @Autowired
    DictService dictService;


    @GetMapping()
    String contentPage() {
        return prefix+"/content";//system文件夹下  content页面
    }

    @GetMapping("/jiangxiang")
    String jiangxiangPage(){
        return "redirect:"+prefix+"/jiangxiang";
    }

    @GetMapping("/prizemy")//点击后跳转到个人论文页面  所以要在model中放一个id
    String paperMy(Model model){
        Long userId=getUser().getUserId();
        model.addAttribute("userId",userId);
        return prefix+"/person";
    }



    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<PrizeDO> paperDOList = prizeService.list(query);
        int total = prizeService.count(query);
        PageUtils pageUtils = new PageUtils(paperDOList, total);
        return pageUtils;//可以写为一行
    }

    @ResponseBody//ajax请求table
    @GetMapping("/myprize/{userId}")//person.js用到list
    public PageUtils listMyPaper(@RequestParam Map<String, Object> params,@PathVariable("userId") Long userId) {
        Query query = new Query(params);
        List<PrizeDO> prizeDOList=prizeService.listMyPrize(query,userId);
        int total = prizeService.count(query);
        PageUtils pageUtils = new PageUtils(prizeDOList, total);
        return pageUtils;//可以写为一行
    }


    @GetMapping("/add")
    String add() {//需不需要这个字典服务
//        model.addAttribute("judgeList",dictService.getJudgeList());//把是否列表放入model
        return prefix+"/add";
    }

    @GetMapping("/add1")//从左边登录的controller
    String add1() {
//        model.addAttribute("judgeList",dictService.getJudgeList());//把是否列表放入model
        return prefix+"/add1";
    }

    @GetMapping("/add2")//从左边登录的controller
    String add2() {
//        model.addAttribute("judgeList",dictService.getJudgeList());//把是否列表放入model
        return prefix+"/add2";
    }
    //点击编辑的时候 和点击add的时候页面差不多但不一样
    @GetMapping("/edit/{prizeId}")
    String edit(@PathVariable("prizeId") Long prizeId, Model model) {
        PrizeDO prizeDO=prizeService.get(prizeId);
        model.addAttribute("prize",prizeDO);
        model.addAttribute("judgeList",dictService.getJudgeList());//把是否列表放入model
        return prefix+"/edit";
    }


    @PostMapping("/updatePrize")
    @ResponseBody
    R updatePaper(PrizeDO prize) {

        if(prizeService.update(prize)>0){
            return R.ok();
        }
        return R.error();
    }



    @PostMapping("/savePrize")//好蠢好蠢
    @ResponseBody
    R savePrize(PrizeDO prize) {
        if(prizeService.save(prize)>0){
            return R.ok();
        }
        return R.error();
    }

    //编辑详情页面
    @GetMapping("/editall/{prizeId}")
    String editAllPage(@PathVariable("prizeId") Long prizeId, Model model) {
        PrizeDO prizeDO=prizeService.get(prizeId);
        model.addAttribute("prize",prizeDO);
        return prefix+"/editall";
    }
    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(PrizeDO prizeDO) {

//        prizeDO.setprizeNew(new Date());//设置最新时间的方式
        int count;
        if(prizeDO.getPrizeId()==null ){
            count=prizeService.save(prizeDO);
        }else{
            count=prizeService.update(prizeDO);
        }
        if(count>0){

            return R.ok().put("prizeId",prizeDO.getPrizeId());
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(PrizeDO prize) {


        prizeService.update(prize);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long prizeId) {

        if (prizeService.remove(prizeId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("prizeIds[]") Long[] prizeIds) {

        prizeService.batchRemove(prizeIds);

        return R.ok();
    }
}
