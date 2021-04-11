package com.bskim.sample.session.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bskim.sample.session.SessionObject;
@Component
public class SessionUtils {
	
	public SessionObject getSessionObject() {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		SessionObject sessionObject = (SessionObject) session.getAttribute("sessionObject");
		return sessionObject;
	}
	
	public void creatSession(SessionObject sessionObject) {
		HttpServletRequest request = getRequest();
		
		//�α��εǾ������� ���� ���� ����
		if(isLogin()) request.getSession().invalidate();
		
		HttpSession session = request.getSession();
		session.setAttribute("sessionObject", sessionObject);
	}
	
	public boolean isLogin() {
		return (getSessionObject() != null);
	}
	
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
}
