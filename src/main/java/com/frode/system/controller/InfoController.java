package com.frode.system.controller;


import com.frode.common.config.Constant;
import com.frode.common.controller.BaseController;
import com.frode.common.utils.PageUtils;
import com.frode.common.utils.Query;
import com.frode.common.utils.R;
import com.frode.system.domain.InfoDO;
import com.frode.system.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/info/content")
public class InfoController extends BaseController {

    private String prefix="system/info";

    @Autowired
    private InfoService infoService;

//    @RequiresPermissions("info:content:content")
    @GetMapping()//权限这里可能有坑
    String bContent() {
        return prefix+"/content";//加左斜线
    }

//    @RequiresPermissions("blog:bContent:bContent")
    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<InfoDO> contentList = infoService.list(query);
        int total = infoService.count(query);
        PageUtils pageUtils = new PageUtils(contentList, total);
        return pageUtils;
    }

//    @RequiresPermissions("blog:bContent:add")
    @GetMapping("/add")
    String add() {
        return prefix+"/add";
    }

//    @RequiresPermissions("blog:bContent:edit")
	@GetMapping("/edit/{infoId}")
	String edit(@PathVariable("infoId") Long infoId, Model model) {
		InfoDO infoDO = infoService.get(infoId);
		model.addAttribute("info", infoDO);
		return prefix+"/edit";
	}

	/**
	 * 保存
	 */
//    @RequiresPermissions("blog:bContent:add")
    @ResponseBody
    @PostMapping("/save")
    public R save(InfoDO info) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "错误");
        }
       /* if (info.getAllowComment() == null) {
            bContent.setAllowComment(0);
        }
        if (bContent.getAllowFeed() == null) {
            bContent.setAllowFeed(0);
        }*/
        if(null==info.getType()) {
            info.setType("article");
        }
        info.setGtmCreate(new Date());
        info.setGtmModified(new Date());
        int count;
        if (info.getInfoId() == null || "".equals(info.getInfoId())) {
            count = infoService.save(info);
        } else {
            count = infoService.update(info);
        }
        if (count > 0) {
            return R.ok().put("infoId", info.getInfoId());
        }
        return R.error();
    }

    /**
     * 修改
     */
//    @RequiresPermissions("blog:bContent:edit")
    @ResponseBody
    @RequestMapping("/update")
    public R update( InfoDO info) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "错误");
        }
        info.setGtmCreate(new Date());
        infoService.update(info);
        return R.ok();
    }

    /**
     * 删除
     */
//    @RequiresPermissions("blog:bContent:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long infoId) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "错误");
        }
        if (infoService.remove(infoId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
//    @RequiresPermissions("blog:bContent:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("infoIds[]") Long[] infoIds) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "错误");
        }
        infoService.batchRemove(infoIds);
        return R.ok();
    }

}
