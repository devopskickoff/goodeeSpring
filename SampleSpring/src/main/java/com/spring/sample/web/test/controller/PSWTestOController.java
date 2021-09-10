package com.spring.sample.web.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.sample.common.bean.PagingBean;
import com.spring.sample.common.service.IPagingService;
import com.spring.sample.web.test.service.PSWITestOService;

@Controller
public class PSWTestOController {

	@Autowired
	public PSWITestOService iTestOService;
	
	@Autowired
	public IPagingService iPagingService;
	
	@RequestMapping(value="/testO")
	public ModelAndView testO (
			@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable {
		int page=1;
		if(params.get("page")!=null) {
			page = Integer.parseInt(params.get("page"));
		}

		int cnt = iTestOService.getO1Cnt(params);
		
		PagingBean pb = iPagingService.getPagingBean(page, cnt,10,3);
		params.put("startCnt", Integer.toString(pb.getStartCount()));
		params.put("endCnt", Integer.toString(pb.getEndCount()));
		
		List<HashMap<String,String>> list = iTestOService.getO1List(params);
		mav.addObject("page",page);
		mav.addObject("pb",pb);
		mav.addObject("list",list);
		mav.setViewName("testO/testO");
		return mav;
	}
	
	@RequestMapping(value="/addO")
	public ModelAndView addO(@RequestParam HashMap<String,String> params,
			ModelAndView mav) throws Throwable {
		int cnt = iTestOService.addO1(params);
		if (cnt > 0) {
			mav.addObject("page",params.get("page"));
			mav.setViewName("redirect:testO");
		} else {
			mav.addObject("msg", "입력에러가 발생했습니다");
			mav.setViewName("testO/failedAction");			
		}
		return mav;
	}
	
	
	@RequestMapping(value="/updateO")
	public ModelAndView updateO (@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable {
		int cnt = iTestOService.updateO1(params);
		
		if (cnt > 0) {
			mav.addObject("page",params.get("page"));
			mav.setViewName("redirect:testO");	
		} else {
			mav.addObject("msg", "수정에러가 발생했습니다");
			mav.setViewName("testO/failedAction");			
		}
		
		return mav;
	}
	
	@RequestMapping(value="/deleteO")
	public ModelAndView deleteO (@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable {
		System.out.println("testttttttttttttt"+params);
		int cnt = iTestOService.deleteO1(params);
		
		if (cnt > 0) {
			mav.addObject("page",params.get("page"));
			mav.setViewName("redirect:testO");
		} else {
			mav.addObject("msg", "삭제에러가 발생했습니다");
			mav.setViewName("testO/failedAction");			
		}
		
		return mav;
	}
}
