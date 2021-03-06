package com.spring.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.service.ITestService;

@Controller
public class TestController {
	
	@Autowired
	public ITestService iTestService;
	
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
			@RequestParam HashMap<String, String> params,
			@RequestParam int c) {
		System.out.println(req.getParameter("msg"));
		System.out.println(msg);
		System.out.println(a);
		System.out.println(params);
		System.out.println(c);
		mav.setViewName("test/test2");
		return mav;
	}
	
	@RequestMapping(value = "/test3")
	public ModelAndView test3(ModelAndView mav) {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		
		for(int i=10; i>0; i--) {
			HashMap<String,String> data = new HashMap<String,String>();
			data.put("no", Integer.toString(i));
			data.put("title","test"+i);
			
			list.add(data);
		}
		
		mav.addObject("list",list);
		mav.setViewName("test/test3");
		return mav;
		
	}
	
	@RequestMapping(value="/test3Dtl")
	public ModelAndView test3Dtl(
						@RequestParam int no,
						ModelAndView mav) {
		iTestService.test();
		
		HashMap<String,String> data = new HashMap<String, String>();
		data.put("no",Integer.toString(no));
		data.put("title","test"+no);
		data.put("writer", "tester"+no);
		data.put("con", "conconcon" + no);
		
		mav.addObject("data",data);
		mav.setViewName("test/test3Dtl");
		return mav;
		
	}
	
	@RequestMapping(value="/testGugu")
	public ModelAndView testGuguRes(ModelAndView mav) {
		mav.setViewName("test/testGugu");
		return mav;
			
	}
	
	@RequestMapping(value="/testGuguRes")
	public ModelAndView testGuguRes(@RequestParam int no, ModelAndView mav) {
		mav.addObject("list",iTestService.gugudan(no));
		mav.setViewName("test/testGuguRes");
		return mav;
			
	}
}
