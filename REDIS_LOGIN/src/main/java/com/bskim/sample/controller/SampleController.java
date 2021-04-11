package com.bskim.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bskim.sample.session.SessionObject;
import com.bskim.sample.session.utils.SessionUtils;

import oracle.jdbc.proxy.annotation.Methods;

@RestController
@RequestMapping("/sample")
public class SampleController {
	
	public SessionUtils sessionUtils;
	@Autowired
	public SampleController(SessionUtils sessionUtils) {
		this.sessionUtils = sessionUtils;
	}
	
	@RequestMapping("/login")
	public @ResponseBody Map<String,Object> login(@RequestBody Map<String,String> param) {
		Map<String,Object> result = new HashMap<String,Object>();
		
		String id = param.get("id");
		String password = param.get("password");
		
		//�α��� ���ռ� ����(DB��ġ�� �ӽ� test)
		if("test".equals(id) && "test".equals(password)) {
			SessionObject sessionObject = new SessionObject();
			sessionObject.setCustNo("123456789");
			sessionObject.setNickName("�г���");
			sessionObject.setUserId(id);
			sessionObject.setUserName("�̸�");
			
			sessionUtils.creatSession(sessionObject);
			result.put("resultMessage", "success");
		}else {
			result.put("resultMessage", "fail");
		}
		
	return result;
	}
	
	@RequestMapping("/sessionChk")
	public @ResponseBody Map<String,Object> sessionChk() {
		Map<String,Object> result = new HashMap<String,Object>();
		
		if(sessionUtils.isLogin()) {
			result.put("sessionObject", sessionUtils.getSessionObject());
			result.put("resultMessage", "success");
		}else {
			result.put("resultMessage", "fail");
		}
		
	return result;
	}
	
}