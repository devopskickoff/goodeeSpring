package com.spring.sample.web.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.sample.common.service.IPagingService;
import com.spring.sample.web.test.service.ITestOService;

@Controller
public class TestOController {

	@Autowired
	public ITestOService iTestOService;
	
	@Autowired
	public IPagingService iPagingService;
	
	@RequestMapping(value="/testO")
	public ModelAndView testO (
			@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable {
		
		List<HashMap<String,String>> list = iTestOService.getO1List(params);
		System.out.println(list);
		mav.addObject("list",list);
		mav.setViewName("testO/testO");
		return mav;
	}
	
	@RequestMapping(value="/addO")
	public ModelAndView addO(@RequestParam HashMap<String,String> params,
			ModelAndView mav) throws Throwable {
		int cnt = iTestOService.addO1(params);
		mav.setViewName("redirect:testO");
		return mav;
	}
	
	
	@RequestMapping(value="/updateO")
	public ModelAndView updateO (@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable {
		int cnt = iTestOService.updateO1(params);
		mav.setViewName("redirect:testO");
		return mav;
	}
	
	@RequestMapping(value="/deleteO")
	public ModelAndView deleteO (@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable {
		System.out.println("testttttttttttttt"+params);
		int cnt = iTestOService.deleteO1(params);
		mav.setViewName("redirect:testO");
		return mav;
	}
}
