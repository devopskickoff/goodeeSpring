package com.spring.sample.web.test.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.sample.common.bean.PagingBean;
import com.spring.sample.common.service.IPagingService;
import com.spring.sample.web.test.service.INTestService;
import com.spring.sample.web.test.service.ITestService;

@Controller
public class NTestController {
	@Autowired
	public INTestService iNTestService;
	
	@Autowired
	public IPagingService iPagingService;
	
	@RequestMapping(value="/mtestList")
	public ModelAndView mtestList(
			@RequestParam HashMap<String,String> params,
			ModelAndView mav) throws Throwable{
		
		/*
		 * int page = 1; //넘어온 페이지 값이 있을 경우 페이지 값 세팅 if(params.get("page")!=null) { page
		 * = Integer.parseInt(params.get("page")); } //글 개수 int cnt =
		 * iNTestService.getM1Cnt(params); PagingBean pb =
		 * iPagingService.getPagingBean(page, cnt, 10,3);
		 * 
		 * params.put("startCnt", Integer.toString(pb.getStartCount()));
		 * params.put("endCnt", Integer.toString(pb.getEndCount()));
		 */
		
		System.out.println(params);
		List<HashMap<String,String>> list = iNTestService.getNTestList(params);
		mav.addObject("list",list);
		mav.setViewName("test/mtestList");
		return mav;
	}
	
	@RequestMapping(value="/mtestDtl")
	public ModelAndView mtestDtl(
			@RequestParam HashMap<String,String> params,
			ModelAndView mav) throws Throwable {

		HashMap<String,String> data = iNTestService.getNtest1(params);
		System.out.println("ttttttttttttttttttteeeeesssssss"+data);
		mav.addObject("data", data);
		mav.setViewName("test/mtestDtl");
		return mav;
	}
	
	@RequestMapping(value="/mtestAdd")
	public ModelAndView mtestAdd(ModelAndView mav) {
		mav.setViewName("test/mtestAdd");
		return mav;
	}
	
	@RequestMapping(value="/mtestAdds")
	public ModelAndView mtestAdds(
			@RequestParam HashMap<String,String> params,
			ModelAndView mav) throws Throwable {
		
		int checkCnt = iNTestService.getM1IdCheck(params);
		
		if ( checkCnt == 0) {
			int cnt = iNTestService.addM1(params);
			if(cnt>0) { //성공
				mav.setViewName("redirect:mtestList");
			} else {
				mav.addObject("msg","회원가입에 실패하였습니다");
				mav.setViewName("test/failedAction");
			}
		} else {
			mav.addObject("msg","중복된 아이디가 있습니다");
			mav.setViewName("test/failedAction");	
		}
		return mav;
	}
	

	@RequestMapping(value="/mtestDelete")
	public ModelAndView mtestDelete(
			@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable{
		int cnt = iNTestService.deleteM1(params);
		if(cnt>0) { //성공
			mav.setViewName("redirect:mtestList");
		} else {
			mav.addObject("msg","회원가입에 실패하였습니다");
			mav.setViewName("test/failedAction");
		}
		return mav;
	}
	
	@RequestMapping(value="/mtestUpdate")
	public ModelAndView mtestUpdate(
			@RequestParam HashMap<String,String> params,
			ModelAndView mav) throws Throwable {
		HashMap<String,String> data = iNTestService.getNtest1(params);
		
		mav.setViewName("test/mtestUpdate");
		return mav;
	}

}
