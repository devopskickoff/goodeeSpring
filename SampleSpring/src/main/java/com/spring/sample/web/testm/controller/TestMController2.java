package com.spring.sample.web.testm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.sample.common.bean.PagingBean;
import com.spring.sample.common.service.IPagingService;
import com.spring.sample.util.Utils;
import com.spring.sample.web.test.service.ITestMService;
import com.spring.sample.web.testm.service.ITestMService2;

@Controller
public class TestMController2 {
	@Autowired
	public ITestMService2 iTestMService;	
	
	@Autowired
	public IPagingService iPagingService;
	
	@RequestMapping(value = "testMList2")
	public ModelAndView testMList2 (
			@RequestParam HashMap<String,String> params,
			ModelAndView mav) {
		String page = "1";
		if(params.get("page")!=null&&params.get("page")!="") {
			page = params.get("page");
		}
		mav.addObject("page", page);
		mav.setViewName("testM2/testMList");
		return mav;
		
	}
	
	@RequestMapping(value="testMLists2",method=RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody 
	public String testMLists2(@RequestParam HashMap<String,String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper(); 
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		int page = Integer.parseInt(params.get("page"));
		int cnt = iTestMService.getMCnt(params);
		
	    System.out.println("cnt"+ cnt);
		
		PagingBean pb = iPagingService.getPagingBean(page, cnt,5,2);
		params.put("startCnt", Integer.toString(pb.getStartCount()));
		params.put("endCnt", Integer.toString(pb.getEndCount()));
		
		
		List<HashMap<String, String>> list = iTestMService.getMList(params);
		//hashmap 하나씩 꺼내서 새로 담기? 
		
		for (HashMap<String, String> hashMap : list) {
			String pw = Utils.decryptAES128(hashMap.get("M_PW"));
			hashMap.put("M_PW", pw);
			System.out.println(hashMap.get("M_PW"));
		}
		
	    // 데이터 담기
	    modelMap.put("list", list);
	    modelMap.put("pb", pb);
	    // 데이터를 문자열화
	    System.out.println("list"+list);

      
      return mapper.writeValueAsString(modelMap);
		
	}
	
	@RequestMapping(value = "/testMAdd2")
	public ModelAndView testM2Add(ModelAndView mav) {
		mav.setViewName("testM2/testMAdd");
		return mav;
	}
	
	@RequestMapping(value="testMAdds2",method= RequestMethod.POST,
			produces = "text/json;charset=UTF-8")
	@ResponseBody
	public String testMAdds2(@RequestParam HashMap<String,String> params) throws Throwable {
	      ObjectMapper mapper = new ObjectMapper();
	      
	      Map<String, Object> modelMap = new HashMap<String, Object>();
	      
	      String result = "success";
	      
	      try {
	    	  String pw = Utils.encryptAES128(params.get("pw"));
	    	  params.put("pw",pw );	
	    	  
	         int cnt= iTestMService.addM1(params);
	         
	         if(cnt == 0) {
	            result="failed";
	         } 
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         
	         result ="error";
	      }
	      
	      modelMap.put("result", result);
	      
	      return mapper.writeValueAsString(modelMap);
	}
	
	
	
	
//	@RequestMapping(value = "/testMList2")
//	public ModelAndView testMList(@RequestParam HashMap<String, String> params,
//								ModelAndView mav) throws Throwable {
//		int page = 1;
//		// 넘어온 페이지 값이 있을 경우 페이지 값 셋팅
//		if(params.get("page") != null) {
//			page = Integer.parseInt(params.get("page"));
//		}
//		// 글 개수 취득
//		int cnt = iTestMService.getM1Cnt(params);
//		// 페이징 계산 후 bean에 데이터 셋팅
//		PagingBean pb = iPagingService.getPagingBean(page, cnt, 10, 3);
//		// 조회 시작 및 종료번호 할당
//		params.put("startCnt", Integer.toString(pb.getStartCount()));
//		params.put("endCnt", Integer.toString(pb.getEndCount()));
//		// 목록데이터 취득
//		List<HashMap<String, String>> list = iTestMService.getM1List(params);
//		
//		mav.addObject("page", page);
//		mav.addObject("pb", pb);
//		mav.addObject("list", list);
//		
//		mav.setViewName("testM2/testMList");
//		
//		return mav;
//	}
	

//	
//	@RequestMapping(value = "/testMAdds")
//	public ModelAndView testMAdds(@RequestParam HashMap<String, String> params,
//								ModelAndView mav) throws Throwable {
//		int checkCnt = iTestMService.getM1IdCheck(params);
//		
//		if(checkCnt == 0) {
//			// 비밀번호 암호화
//			String pw = Utils.encryptAES128(params.get("pw"));
//			params.put("pw",pw );			
//			
//			int cnt = iTestMService.addM1(params);
//			
//			if(cnt > 0) {
//				mav.setViewName("redirect:testMList");
//			} else {
//				mav.addObject("msg", "등록중 문제가 발생했습니다.");
//				mav.setViewName("testM/failedAction");
//			}
//		} else {
//			mav.addObject("msg", "중복된 아이디가 있습니다.");
//			mav.setViewName("testM/failedAction");
//		}
//		
//		return mav;
//	}
//	
	@RequestMapping(value = "/testMDtl2")
	public ModelAndView testMDtl2(@RequestParam HashMap<String, String> params,
							ModelAndView mav) throws Throwable {		
		HashMap<String, String> data = iTestMService.getM1(params);
		
		String pw = Utils.decryptAES128(data.get("M_PW"));
		data.put("M_PW", pw);
		mav.addObject("data", data);
		mav.setViewName("testM2/testMDtl");
		return mav;
	}
	
	@RequestMapping(value = "/testMUpdate2")
	public ModelAndView testMUpdate2(@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable {		
		HashMap<String, String> data = iTestMService.getM1(params);
		// 비밀번호 복호화

		String pw = Utils.decryptAES128(data.get("M_PW"));
		System.out.println("tttttttttttttt"+pw);
		data.put("M_PW", pw);
		
		mav.addObject("data", data);
		mav.setViewName("testM2/testMUpdate");
		
		return mav;
	}

	@RequestMapping(value="testMUpdates2", method= RequestMethod.POST,
		         produces = "text/json;charset=UTF-8" )
	@ResponseBody 
	public String testMUpdates2(@RequestParam HashMap<String, String> params) throws Throwable {	
	      ObjectMapper mapper = new ObjectMapper();
	      
	      Map<String, Object> modelMap = new HashMap<String, Object>();
	      
	      String result = "success";
	      
	      try {
	         int cnt= iTestMService.updateM1(params);
	         
	         if(cnt == 0) {
	            result="failed";
	         } 
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         
	         result ="error";
	      }
	      
	      modelMap.put("result", result);
	      
	      return mapper.writeValueAsString(modelMap);
		
	}
//	
	  @RequestMapping(value="testMDelete2", method= RequestMethod.POST,
		         produces = "text/json;charset=UTF-8" )
		   @ResponseBody 
	public String testMDelete2(@RequestParam HashMap<String, String> params) throws Throwable {
	      ObjectMapper mapper = new ObjectMapper();
	      
	      Map<String, Object> modelMap = new HashMap<String, Object>();
	      
	      String result = "success";
	      
	      try {
	         int cnt= iTestMService.deleteM1(params);
	         
	         if(cnt == 0) {
	            result="failed";
	         } 
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         
	         result ="error";
	      }
	      
	      modelMap.put("result", result);
	      
	      return mapper.writeValueAsString(modelMap);

	}
//	
//	@RequestMapping(value="/testLogin")
//	public ModelAndView testLogin(HttpSession session,
//					ModelAndView mav) {
//		if(session.getAttribute("sMNo")!=null) { // 로그인 중
//			mav.setViewName("redirect:testList");
//		} else { // 로그인 안된 상태
//			mav.setViewName("testM/testLogin");
//		}		
//		
//		return mav;
//	}
//	
//	@RequestMapping(value="/testLogins")
//	public ModelAndView testLogins(@RequestParam HashMap<String, String> params,
////							HttpServletRequest req, // 1번 취득방식
//							HttpSession session, // 2번 취득방식
//							ModelAndView mav) throws Throwable {
//		// 비밀번호 암호화
//		String pw = Utils.encryptAES128(params.get("pw"));
//		params.put("pw",pw );
//		
//		
//		HashMap<String, String> data = iTestMService.getM1Login(params);
//		
//		if(data != null) { // 사용자 데이터가 있을경우
//			// session 취득방법
//			// 1. request를 활용
//			// 2. 인자를 통하여 session 요청
//			// HttpSession session =req.getSession(); // 1번 취득방식
//			// setAttribute(키,값) : 세션에 해당 키와 값을 넣는다.
//			session.setAttribute("sMNo", data.get("M_NO"));
//			session.setAttribute("sMNm", data.get("M_NM"));
//			// getAttribute(키) : 세션에서 키에 해당하는 값을 가져온다
//			System.out.println(session.getAttribute("sMNm"));
//			
//			mav.setViewName("redirect:testO");
//		} else { // 없을 경우(로그인 실패)
//			mav.addObject("msg","아이디나 비밀번호가 틀립니다.");
//			mav.setViewName("testM/failedAction");
//		}		
//		return mav;
//	}
//	
//	@RequestMapping(value="/testLogout")
//	public ModelAndView testLogout(HttpSession session, ModelAndView mav) {
//		
//		session.invalidate(); // session정보 초기화
//		
//		mav.setViewName("redirect:testLogin");
//		
//		return mav;
//	}
	
}















