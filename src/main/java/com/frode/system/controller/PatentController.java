package com.frode.system.controller;

import com.frode.common.controller.BaseController;
import com.frode.common.service.DictService;
import com.frode.common.utils.PageUtils;
import com.frode.common.utils.Query;
import com.frode.common.utils.R;
import com.frode.system.domain.PatentDO;
import com.frode.system.service.PatentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/patent/content")
public class PatentController extends BaseController {

    private String prefix="system/patent";

    @Autowired
    PatentService patentService;

    @Autowired
    DictService dictService;


    @GetMapping()
    String contentPage() {
        return prefix+"/content";//system文件夹下  content页面
    }

    @GetMapping("/zhuanli")
    String zhuanliPage(){
        return "redirect:"+prefix+"/zhuanli";
    }




    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<PatentDO> paperDOList = patentService.list(query);
        int total = patentService.count(query);
        PageUtils pageUtils = new PageUtils(paperDOList, total);
        return pageUtils;//可以写为一行
    }

    @GetMapping("/patentmy")//
    String patentMy(Model model){
        Long userId=getUser().getUserId();
        model.addAttribute("userId",userId);
        return prefix+"/person";
    }

    @ResponseBody//ajax请求table
    @GetMapping("/mypatent/{userId}")//person.js用到list
    public PageUtils listMyPatent(@RequestParam Map<String, Object> params,@PathVariable("userId") Long userId) {
        Query query = new Query(params);
        List<PatentDO> patentDOList=patentService.listMyPatent(query,userId);
        int total = patentService.count(query);
        PageUtils pageUtils = new PageUtils(patentDOList, total);
        return pageUtils;//可以写为一行
    }

    @GetMapping("/add")
    String add(Model model) {//需不需要这个字典服务
//        model.addAttribute("patentTypeList",dictService.getPatentTypeList());
//        return prefix+"/add";
        return "system/patent/add";
    }


    @GetMapping("/add1")//从左边登录的controller
    String add1Page() {//专利类型
//        model.addAttribute("patentTypeList",dictService.getPatentTypeList());
        return "system/patent/add1";
    }

    @GetMapping("/add2")//从左边登录的controller
    String add2() {

        return prefix+"/add2";
    }

    //点击编辑的时候 和点击add的时候页面差不多但不一样
    @GetMapping("/edit/{patentId}")
    String edit(@PathVariable("patentId") Long patentId, Model model) {
        PatentDO patentDO=patentService.get(patentId);
        model.addAttribute("patent",patentDO);
//        model.addAttribute("patentTypeList",dictService.getPatentTypeList());
        return prefix+"/edit";
    }


    @PostMapping("/updatePatent")
    @ResponseBody
    R updatePaper(PatentDO patent) {

        if(patentService.update(patent)>0){
            return R.ok();
        }
        return R.error();
    }



    @PostMapping("/savePatent")
    @ResponseBody
    R savePatent(PatentDO patent) {
        if(patentService.save(patent)>0){
            return R.ok();
        }
        return R.error();
    }

    //编辑详情页面
    @GetMapping("/editall/{patentId}")
    String editAllPage(@PathVariable("patentId") Long patentId, Model model) {
        PatentDO patentDO=patentService.get(patentId);
        model.addAttribute("patent",patentDO);
        return prefix+"/editall";
    }
    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(PatentDO patentDO) {


        int count;
        if(patentDO.getPatentId()==null){
            count=patentService.save(patentDO);
        }else{
            count=patentService.update(patentDO);
        }
        if(count>0){

            return R.ok().put("patentId",patentDO.getPatentId());
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(PatentDO patent) {

        patentService.update(patent);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long patentId) {

        if (patentService.remove(patentId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("patentIds[]") Long[] patentIds) {

        patentService.batchRemove(patentIds);

        return R.ok();
    }
}
