package com.spring.sample.web.test.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.sample.web.test.service.ITestService;

@Controller
public class TestController {
	@Autowired
	public ITestService iTestService;
	
	@RequestMapping(value="/testList")
	public ModelAndView testList(
			@RequestParam HashMap<String,String> params,
			ModelAndView mav) throws Throwable{
		List<HashMap<String,String>> list = iTestService.getB1List(params);
		System.out.println(list);
		mav.addObject("list", list);
		mav.setViewName("test/testList");
		return mav;
	}
	
	@RequestMapping(value="/testDtl")
	public ModelAndView testDtl(ModelAndView mav,
			@RequestParam HashMap<String,String> params) throws Throwable {
		HashMap<String,String> data = iTestService.getB1(params);
		mav.addObject("data", data);
		mav.setViewName("test/testDtl");
		return mav;
	}
}
