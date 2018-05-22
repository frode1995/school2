package com.frode.common.controller;

import org.springframework.stereotype.Controller;
import com.frode.common.utils.ShiroUtils;
import com.frode.system.domain.UserDO;

@Controller
public class BaseController {//shiro得到当前id
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}