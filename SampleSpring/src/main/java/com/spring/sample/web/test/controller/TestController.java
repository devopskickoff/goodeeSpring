package com.spring.sample.web.test.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.sample.web.test.service.ITestService;

@Controller
public class TestController {
	@Autowired
	public ITestService iTestService;
	
	@RequestMapping(value="/testList")
	public ModelAndView testList(ModelAndView mav) throws Throwable{
		List<HashMap<String,String>> list = iTestService.getB1List();
		mav.setViewName("test/testList");
		return mav;
	}
}
