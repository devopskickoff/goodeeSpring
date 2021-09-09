package com.spring.sample.web.test.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.sample.common.bean.PagingBean;
import com.spring.sample.common.service.IPagingService;
import com.spring.sample.util.Utils;
import com.spring.sample.web.test.service.ITestMService;

@Controller
public class TestMController {
	@Autowired
	public ITestMService iTestMService;	
	
	@Autowired
	public IPagingService iPagingService;
	
	@RequestMapping(value = "/testMList")
	public ModelAndView testMList(@RequestParam HashMap<String, String> params,
								ModelAndView mav) throws Throwable {
		int page = 1;
		// 넘어온 페이지 값이 있을 경우 페이지 값 셋팅
		if(params.get("page") != null) {
			page = Integer.parseInt(params.get("page"));
		}
		// 글 개수 취득
		int cnt = iTestMService.getM1Cnt(params);
		// 페이징 계산 후 bean에 데이터 셋팅
		PagingBean pb = iPagingService.getPagingBean(page, cnt, 10, 3);
		// 조회 시작 및 종료번호 할당
		params.put("startCnt", Integer.toString(pb.getStartCount()));
		params.put("endCnt", Integer.toString(pb.getEndCount()));
		// 목록데이터 취득
		List<HashMap<String, String>> list = iTestMService.getM1List(params);
		
		mav.addObject("page", page);
		mav.addObject("pb", pb);
		mav.addObject("list", list);
		
		mav.setViewName("testM/testMList");
		
		return mav;
	}
	
	@RequestMapping(value = "/testMAdd")
	public ModelAndView testMAdd(ModelAndView mav) {
		mav.setViewName("testM/testMAdd");
		
		return mav;
	}
	
	@RequestMapping(value = "/testMAdds")
	public ModelAndView testMAdds(@RequestParam HashMap<String, String> params,
								ModelAndView mav) throws Throwable {
		int checkCnt = iTestMService.getM1IdCheck(params);
		
		if(checkCnt == 0) {
			// 비밀번호 암호화
			String pw = Utils.encryptAES128(params.get("pw"));
			params.put("pw",pw );			
			
			int cnt = iTestMService.addM1(params);
			
			if(cnt > 0) {
				mav.setViewName("redirect:testMList");
			} else {
				mav.addObject("msg", "등록중 문제가 발생했습니다.");
				mav.setViewName("testM/failedAction");
			}
		} else {
			mav.addObject("msg", "중복된 아이디가 있습니다.");
			mav.setViewName("testM/failedAction");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/testMDtl")
	public ModelAndView testMDtl(@RequestParam HashMap<String, String> params,
							ModelAndView mav) throws Throwable {		
		HashMap<String, String> data = iTestMService.getM1(params);
		
		mav.addObject("data", data);
		
		mav.setViewName("testM/testMDtl");
		
		return mav;
	}
	
	@RequestMapping(value = "/testMUpdate")
	public ModelAndView testMUpdate(@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable {		
		HashMap<String, String> data = iTestMService.getM1(params);
		// 비밀번호 복호화

		String pw = Utils.decryptAES128(data.get("M_PW"));
		System.out.println("tttttttttttttt"+pw);
		data.put("M_PW", pw);
		
		mav.addObject("data", data);
		mav.setViewName("testM/testMUpdate");
		
		return mav;
	}

	@RequestMapping(value = "/testMUpdates")
	public ModelAndView testMUpdates(@RequestParam HashMap<String, String> params,
								ModelAndView mav) throws Throwable {
		// 비밀번호 암호화
		String pw = Utils.encryptAES128(params.get("pw"));
		params.put("pw",pw );
		
		int cnt = iTestMService.updateM1(params);
		
		if(cnt > 0) {
			mav.setViewName("testM/testMUpdates");
		} else {
			mav.addObject("msg", "수정중 문제가 발생했습니다.");
			mav.setViewName("testM/failedAction");
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/testMDelete")
	public ModelAndView testMDelete(@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable {
		int cnt = iTestMService.deleteM1(params);
		
		if(cnt > 0) {
			mav.setViewName("redirect:testMList");
		} else {
			mav.addObject("msg", "삭제중 문제가 발생했습니다.");
			mav.setViewName("testM/failedAction");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/testLogin")
	public ModelAndView testLogin(HttpSession session,
					ModelAndView mav) {
		if(session.getAttribute("sMNo")!=null) { // 로그인 중
			mav.setViewName("redirect:testList");
		} else { // 로그인 안된 상태
			mav.setViewName("testM/testLogin");
		}		
		
		return mav;
	}
	
	@RequestMapping(value="/testLogins")
	public ModelAndView testLogins(@RequestParam HashMap<String, String> params,
//							HttpServletRequest req, // 1번 취득방식
							HttpSession session, // 2번 취득방식
							ModelAndView mav) throws Throwable {
		// 비밀번호 암호화
		String pw = Utils.encryptAES128(params.get("pw"));
		params.put("pw",pw );
		
		
		HashMap<String, String> data = iTestMService.getM1Login(params);
		
		if(data != null) { // 사용자 데이터가 있을경우
			// session 취득방법
			// 1. request를 활용
			// 2. 인자를 통하여 session 요청
			// HttpSession session =req.getSession(); // 1번 취득방식
			// setAttribute(키,값) : 세션에 해당 키와 값을 넣는다.
			session.setAttribute("sMNo", data.get("M_NO"));
			session.setAttribute("sMNm", data.get("M_NM"));
			// getAttribute(키) : 세션에서 키에 해당하는 값을 가져온다
			System.out.println(session.getAttribute("sMNm"));
			
			mav.setViewName("redirect:testO");
		} else { // 없을 경우(로그인 실패)
			mav.addObject("msg","아이디나 비밀번호가 틀립니다.");
			mav.setViewName("testM/failedAction");
		}		
		return mav;
	}
	
	@RequestMapping(value="/testLogout")
	public ModelAndView testLogout(HttpSession session, ModelAndView mav) {
		
		session.invalidate(); // session정보 초기화
		
		mav.setViewName("redirect:testLogin");
		
		return mav;
	}
	
}















