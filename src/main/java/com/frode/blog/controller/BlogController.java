package com.frode.blog.controller;

import com.frode.blog.domain.ContentDO;
import com.frode.blog.service.ContentService;
import com.frode.common.utils.DateUtils;
import com.frode.common.utils.PageUtils;
import com.frode.common.utils.Query;
import com.frode.system.service.PaperService;
import com.frode.system.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@RequestMapping("/blog")//
@Controller//整体预览界面
public class BlogController {
	@Autowired
    ContentService bContentService;

	@Autowired
	PaperService paperService;

	@Autowired
	TestService testService;

/*	@GetMapping("blog/first")
	String firstPage(){
		return "blog/first1";
	}*/

	@GetMapping("/blog")
	String blog() {
//		return "/blog/index/first";
		return "blog/index/main";
	}

	@GetMapping("/blog/first")
	String first() {//home 页面
		return "/blog/index/first";
	}

	@GetMapping("/blog/login")//测试login
	String loginPage() {//home 页面
		return "/blog/index/login";
	}

	@GetMapping("/first")
	String firstPage(){
		return "first";
	}

	//统计的几个controller
	@GetMapping("/blog/tongji")
	String tongjiPage(){
		return "blog/index/tongji";
	}

	//上传单个文件  这里是处理xls文件的控制器
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> upload(@RequestParam(value = "uploadFileName",required = false) MultipartFile file) throws Exception {

		Map<String,String> modelMap=new HashMap<>();
		if("true".equals(testService.xlsToDB(file))){
			modelMap.put("status","success");
		}else {
			modelMap.put("status","fail");
		}
		return modelMap;

	}


	@ResponseBody
	@GetMapping("/blog/open/list")
	public PageUtils opentList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<ContentDO> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		return pageUtils;
	}

	@GetMapping("/blog/open/post/{cid}")
	String post(@PathVariable("cid") Long cid, Model model) {
		ContentDO bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);//处理到秒的时间
		model.addAttribute("gtmModified", DateUtils.format(bContentDO.getGtmModified()));
		return "blog/index/post";
	}



	@GetMapping("/blog/open/page/{categories}")
	String about(@PathVariable("categories") String categories, Model model) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("categories", categories);
		ContentDO bContentDO =null;
		if(bContentService.list(map).size()>0){
			 bContentDO = bContentService.list(map).get(0);
		}
		model.addAttribute("bContent", bContentDO);
		return "blog/index/post";
	}

	@GetMapping("/blog/preview/{categories}")
	String previewPage(@PathVariable("categories") String categories, Model model) {
		Map<String, Object> map = new HashMap<>(16);
		map.put("categories", categories);
		ContentDO bContentDO =null;
		if(bContentService.list(map).size()>0){
			bContentDO = bContentService.list(map).get(0);
		}
		model.addAttribute("bContent", bContentDO);
		return "blog/index/post";
	}

}
