package com.frode.system.controller;

import com.frode.common.controller.BaseController;
import com.frode.common.service.DictService;
import com.frode.common.utils.PageUtils;
import com.frode.common.utils.Query;
import com.frode.common.utils.R;
import com.frode.system.domain.ProjectDO;
import com.frode.system.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/project/content")
public class ProjectController extends BaseController{
    private String prefix="system/project";

    @Autowired
    ProjectService projectService;

    @Autowired
    DictService dictService;


    @GetMapping()
    String contentPage() {
        return prefix+"/content";//system文件夹下  content页面
    }

    @GetMapping("/xiangmu")
    String xiangmuPage(){
        return "redirect:"+prefix+"/xiangmu";
    }

    @GetMapping("/projectmy")//点击后跳转到个人项目页面  所以要在model中放一个id
    String paperMy(Model model){
        Long userId=getUser().getUserId();
        model.addAttribute("userId",userId);
        return prefix+"/person";
    }

    @GetMapping("/blog/open/post/{projectId}")
    String previewProject(@PathVariable("projectId") Long projectId, Model model){
        ProjectDO projectDO=projectService.get(projectId);
        model.addAttribute("project",projectDO);
        model.addAttribute("projectNew",projectDO.getProjectNew());
        return "blog/index/post";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<ProjectDO> paperDOList = projectService.list(query);
        int total = projectService.count(query);
        PageUtils pageUtils = new PageUtils(paperDOList, total);
        return pageUtils;//可以写为一行
    }

    @ResponseBody//ajax请求table
    @GetMapping("/myproject/{userId}")//person.js用到list
    public PageUtils listMyPaper(@RequestParam Map<String, Object> params,@PathVariable("userId") Long userId) {
        Query query = new Query(params);
        List<ProjectDO> projectDOList=projectService.listMyProject(query,userId);
        int total = projectService.count(query);
        PageUtils pageUtils = new PageUtils(projectDOList, total);
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
    @GetMapping("/edit/{projectId}")
    String edit(@PathVariable("projectId") Long projectId, Model model) {
        ProjectDO projectDO=projectService.get(projectId);
        model.addAttribute("project",projectDO);
        model.addAttribute("judgeList",dictService.getJudgeList());//把是否列表放入model
        return prefix+"/edit";
    }


    @PostMapping("/updateProject")
    @ResponseBody
    R updatePaper(ProjectDO project) {

        if(projectService.update(project)>0){
            return R.ok();
        }
        return R.error();
    }



    @PostMapping("/saveProject")//好蠢好蠢
    @ResponseBody
    R saveProject(ProjectDO project) {
        if(projectService.save(project)>0){
            return R.ok();
        }
        return R.error();
    }

    //编辑详情页面
    @GetMapping("/editall/{projectId}")
    String editAllPage(@PathVariable("projectId") Long projectId, Model model) {
        ProjectDO projectDO=projectService.get(projectId);
        model.addAttribute("project",projectDO);
        return prefix+"/editall";
    }
    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(ProjectDO projectDO) {

//        projectDO.setProjectNew(new Date());//设置最新时间的方式
        int count;
        if(projectDO.getProjectId()==null ){
            count=projectService.save(projectDO);
        }else{
            count=projectService.update(projectDO);
        }
        if(count>0){

            return R.ok().put("projectId",projectDO.getProjectId());
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(ProjectDO project) {

        project.setProjectNew(new Date());//设置更改时间
        projectService.update(project);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long projectId) {

        if (projectService.remove(projectId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("projectIds[]") Long[] projectIds) {

        projectService.batchRemove(projectIds);

        return R.ok();
    }
}
