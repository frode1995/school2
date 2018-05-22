package com.frode.oa.controller;


import com.frode.oa.domain.Response;
import com.frode.system.service.SessionService;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import java.security.Principal;


@Controller
public class WebSocketController {
	@Autowired
	SimpMessagingTemplate template;

	@Autowired
	SessionService sessionService;


	@GetMapping("/test")
	String test() {
		return "test";
	}

}