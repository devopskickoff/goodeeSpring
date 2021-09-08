package com.spring.sample.web.test.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.sample.common.bean.PagingBean;
import com.spring.sample.common.service.IPagingService;
import com.spring.sample.web.test.service.ITestService;

@Controller
public class TestController {
	@Autowired
	public ITestService iTestService;
	
	@Autowired
	public IPagingService iPagingService;
	
	@RequestMapping(value="/testList")
	public ModelAndView testList(
			@RequestParam HashMap<String,String> params,
			ModelAndView mav) throws Throwable{
		
		int page = 1; //현재 p변수
		if(params.get("page")!=null) { //넘어오는 현재 p데이터가 존재 시 
			page = Integer.parseInt(params.get("page"));
		}
		
		int cnt = iTestService.getB1Cnt(params); //총 게시글 개수
		
		PagingBean pb = iPagingService.getPagingBean(page, cnt);
		params.put("startCnt",Integer.toString(pb.getStartCount()));
		params.put("endCnt", Integer.toString(pb.getEndCount()));
		
		List<HashMap<String,String>> list = iTestService.getB1List(params);
		mav.addObject("page", page);
		mav.addObject("pb", pb);
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
	
	@RequestMapping(value="/testAdd")
	public ModelAndView testAdd(ModelAndView mav) {
		mav.setViewName("test/testAdd");
		return mav;
	}
	
	@RequestMapping(value="/testAdds")
	public ModelAndView testAdds(ModelAndView mav,
			@RequestParam HashMap<String,String> params) throws Throwable{
		System.out.println("testttttttttttt"+params);
		int cnt = iTestService.addB1(params);
		
		if(cnt>0) { //추가성공
			mav.setViewName("redirect:testList");
		} else {
			mav.addObject("msg","저장에 실패하였습니다");
			mav.setViewName("test/failedAction");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/testUpdate")
	public ModelAndView testUpdate(ModelAndView mav,
			@RequestParam HashMap<String,String> params) throws Throwable {
		HashMap<String,String> data = iTestService.getB1(params);
		mav.addObject("data", data);
		mav.setViewName("test/testUpdate");
		return mav;
	}
	
	@RequestMapping(value="/testUpdates")
	public ModelAndView testUpdates(ModelAndView mav,
			@RequestParam HashMap<String,String> params) throws Throwable{
		int cnt = iTestService.updateB1(params);
		
		if(cnt>0) {
			mav.setViewName("test/testUpdates");
		} else {
			mav.addObject("msg","수정에 실패하였습니다");
			mav.setViewName("test/failedAction");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/testDelete")
	public ModelAndView testDelete(ModelAndView mav,
			@RequestParam HashMap<String,String> params) throws Throwable{
		int cnt = iTestService.deleteB1(params);
		
		if(cnt>0) { //삭제성공
			mav.setViewName("redirect:testList");
		} else {
			mav.addObject("msg","삭제에 실패하였습니다");
			mav.setViewName("test/failedAction");
		}
		
		return mav;
	}
	
}
