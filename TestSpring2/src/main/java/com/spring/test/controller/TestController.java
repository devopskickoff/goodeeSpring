package com.spring.test.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	
	@RequestMapping(value = "/test")
	//ModelAndView : 데이터와 뷰정보를 담고 있다.
	public ModelAndView test(ModelAndView mav) {
		// viewResolver => "WEB-INF/views/"+"test/test" + ".jsp"
		//				=> "WEB-INF/views/test/test.jsp"
		mav.setViewName("test/test");
		mav.addObject("a","aaaa");
		return mav;
	}
	
	@RequestMapping(value = "/testRec")
	public ModelAndView test2 (ModelAndView mav, 
			HttpServletRequest req,//request 객체변수 선언
			//RequestParm에서 required=false는 필수항목은 아님을 설정
			@RequestParam(required = false) String msg, //넘어오는 데이터 중 변수명과 동일한 키값을 가진다
			@RequestParam(value="msg") String a,
			@RequestParam HashMap<String, String> params) {
		System.out.println(req.getParameter("msg"));
		System.out.println(msg);
		System.out.println(a);
		System.out.println(params);
		mav.setViewName("test/test2");
		return mav;
	}
}
