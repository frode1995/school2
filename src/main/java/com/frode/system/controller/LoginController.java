package com.frode.system.controller;

import com.frode.common.annotation.Log;
import com.frode.common.controller.BaseController;
import com.frode.common.domain.FileDO;
import com.frode.common.domain.Tree;
import com.frode.common.service.FileService;
import com.frode.common.utils.MD5Utils;
import com.frode.common.utils.R;
import com.frode.common.utils.ShiroUtils;
import com.frode.system.dao.UserDao;
import com.frode.system.domain.MenuDO;
import com.frode.system.service.MenuService;
import com.frode.system.service.SessionService;
import io.swagger.models.auth.In;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;

	@Autowired
	SessionService sessionService;

/*	@Autowired
	 UserDao userMapper;*/





	@GetMapping({ "/", "" })//默认控制器跳转到first
	String welcome(Model model) {

		return "redirect:/first";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })//返回页面
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());//在请求这个页面之前存储id
//		FileDO fileDO = fileService.get(getUser().getPicId());
		FileDO fileDO = fileService.get(getUser().getPicId());//id  相对路径
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				String url=fileDO.getUrl();
				System.out.println(url);
				//String prefix = "D:/school/files/";
				//String filePath=prefix+url;
				model.addAttribute("picUrl",url);//用的绝对路径 最好相对路径
			}else {
//				model.addAttribute("picUrl","/img/photo_s.jpg");
				model.addAttribute("picUrl","#");
			}
		}else {
//			model.addAttribute("picUrl","/img/photo_s.jpg");
			model.addAttribute("picUrl","#");
		}
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password, HttpServletRequest request) {
		password = MD5Utils.encrypt(username, password);//生成MD5加密的密码
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/first";
//		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

}
