package com.frode.system.controller;

import com.frode.common.config.Constant;
import com.frode.common.controller.BaseController;
import com.frode.common.service.DictService;
import com.frode.common.utils.PageUtils;
import com.frode.common.utils.Query;
import com.frode.common.utils.R;
import com.frode.system.domain.PaperDO;
import com.frode.system.service.CountService;
import com.frode.system.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/paper/content")
public class PaperController extends BaseController {

    private String prefix="system/paper";

    @Autowired
    PaperService paperService;

    @Autowired
    DictService dictService;

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

        return countService.getImgUrl(deptId);

    }

/*    @GetMapping("/count2/{deptId}")//选中后刷新 加入model后的 跳转页面
    public String count2(@PathVariable("deptId") int deptId,Model model){
        String imgUrl="";
//        if("6".equals(deptId)){//再次跳转成字符串  如果是老师
//            imgUrl="../../img/table/barchart.jpg";
        if(deptId==6){
            imgUrl="/img/table/barchart.jpg";
        }
        model.addAttribute("imgUrl",imgUrl);

        return  "system/count/users";

    }*/

    @GetMapping("/count/cs")
    public String csPage(Model model){
        String imgUrl="/img/table/barchart.jpg";
        model.addAttribute("imgUrl",imgUrl);
        return "system/count/cs";
    }

    @GetMapping("/count/guanli")
    public String guanliPage(Model model){
        String imgUrl="/img/table/guanlichart.jpg";
        model.addAttribute("imgUrl",imgUrl);
        return "system/count/guanli";
    }

    @GetMapping("/count/xindian")
    public String xindianPage(){
        return "system/count/xindian";
    }

    @GetMapping("/count/art")
    public String artPage(){
        return "system/count/art";
    }

    @GetMapping()
    String contentPage() {
        return prefix+"/content";//system文件夹下  content页面
    }

   /* @GetMapping("/papermy/{userId}")
    String paperMy(@PathVariable("userId") Long userId,Model model, HttpServletRequest request){
//        Long userId=getUser().getUserId();
        model.addAttribute("userId",userId);
        return prefix+"/person"+userId;//返回当前userId的person页面
    }*/
   //上传单个文件  这里是处理xls文件的控制器
//   @ResponseBody
   @RequestMapping(value="/xls",method = RequestMethod.POST)
   public String upload(@RequestParam(value = "uploadFileName",required = false) MultipartFile file) throws Exception {

       if("true".equals(paperService.xlsToDB(file))){
           return prefix+"/content";
       }else {
           return prefix+"/content";
       }

   }

    @RequestMapping("/downloadxls")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response){
            String fileName = "paper.xls";//论文xls格式
            String path="C:/xlsformat/";
            File file = new File(path, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" +  fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        System.out.println("success");

    }

   @GetMapping("/papermy")//点击后跳转到个人论文页面  所以要在model中放一个id
   String paperMy(Model model){
       Long userId=getUser().getUserId();
       model.addAttribute("userId",userId);
       return prefix+"/person";
   }

    @GetMapping("/lunwen")
    String lunwenPage(){
        return prefix+"/lunwen";
    }



    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<PaperDO> paperDOList = paperService.list(query);
        int total = paperService.count(query);
        PageUtils pageUtils = new PageUtils(paperDOList, total);
        return pageUtils;//可以写为一行
    }

 /*   @ResponseBody//ajax请求table
    @GetMapping("/mypaper/{userId}")//person.js用到list
    public PageUtils listMyPaper(@RequestParam @Param("params") Map<String, Object> params, @PathVariable("userId") Long userId) {
        Query query = new Query(params);
        List<PaperDO> paperDOList = paperService.listMyPaper(query,userId);
        int total = paperService.count(query);
        PageUtils pageUtils = new PageUtils(paperDOList, total);
        return pageUtils;//可以写为一行
    }*/

    @ResponseBody//ajax请求table
    @GetMapping("/mypaper/{userId}")//person.js用到list
    public PageUtils listMyPaper(@RequestParam Map<String, Object> params,@PathVariable("userId") Long userId) {
        Query query = new Query(params);
        List<PaperDO> paperDOList = paperService.listMyPaper(query,userId);
        int total = paperService.count(query);
        PageUtils pageUtils = new PageUtils(paperDOList, total);
        return pageUtils;//可以写为一行
    }

    @GetMapping("/add")
    String add(Model model) {
        model.addAttribute("judgeList",dictService.getJudgeList());//把是否列表放入model
        return prefix+"/add";
    }

    @GetMapping("/add1")//从左边登录的controller
    String add1(Model model) {
        model.addAttribute("judgeList",dictService.getJudgeList());//把是否列表放入model
        return prefix+"/add1";
    }

    @GetMapping("/add2")//从左边登录的controller  老师的添加 为了跳转到自己的论文
    String add2(Model model) {
        model.addAttribute("judgeList",dictService.getJudgeList());//把是否列表放入model
        return prefix+"/add2";
    }

//点击编辑的时候 和点击add的时候页面差不多但不一样
    @GetMapping("/edit/{paperId}")
    String edit(@PathVariable("paperId") Long paperId, Model model) {
        PaperDO paperDO=paperService.get(paperId);
        model.addAttribute("paper",paperDO);
        model.addAttribute("judgeList",dictService.getJudgeList());//把是否列表放入model
        return prefix+"/edit";
    }

//    @Log("更新论文信息")
    @PostMapping("/updatePaper")
    @ResponseBody
    R updatePaper(PaperDO paper) {
        //paper.setPaperNew(new Date());//更新时间写在service层更好
        if(paperService.update(paper)>0){
            return R.ok();
        }
        return R.error();
    }



    @PostMapping("/savePaper")
    @ResponseBody
    R savePaper(PaperDO paper) {
        if(paperService.save(paper)>0){
            return R.ok();
        }
        return R.error();
    }

    //编辑详情页面
    @GetMapping("/editall/{paperId}")
    String editAllPage(@PathVariable("paperId") Long paperId, Model model) {
        PaperDO paper=paperService.get(paperId);
        model.addAttribute("paper",paper);
        return prefix+"/editall";
    }
    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(PaperDO paperDO) {

//        paperDO.setPaperNew(new Date());//设置最新时间的方式
        int count;
        if(paperDO.getPaperId()==null ){
            count=paperService.save(paperDO);
        }else{
            count=paperService.update(paperDO);
        }
        if(count>0){

            return R.ok().put("paperId",paperDO.getPaperId());
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(PaperDO paper) {

        paper.setPaperNew(new Date());//设置更改时间
        paperService.update(paper);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long paperId) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "错误");
        }
        if (paperService.remove(paperId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("paperIds[]") Long[] paperIds) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "错误");
        }
        paperService.batchRemove(paperIds);

        return R.ok();
    }



}
