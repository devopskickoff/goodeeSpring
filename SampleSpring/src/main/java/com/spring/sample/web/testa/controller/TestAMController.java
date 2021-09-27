package com.spring.sample.web.testa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.spring.sample.web.testa.service.ITestAMService;

@Controller
public class TestAMController {

	@Autowired
	public ITestAMService iTestMAService;
	
	@Autowired
	public IPagingService iPagingService;
	
	@RequestMapping(value="/testAMList")
	public ModelAndView testAMList(@RequestParam HashMap<String,String> params,
									ModelAndView mav) {
		int page = 1;
		if(params.get("page")!=null) {
			page = Integer.parseInt(params.get("page"));
		}
		
		mav.addObject("page",page);
		
		mav.setViewName("testa/testAMList");
		
		return mav;
	}
	
	@RequestMapping(value = "/testAMListAjax", method=RequestMethod.POST,
			produces = "text/json; charset=UTF-8")
	@ResponseBody
	public String testAMListAjax(@RequestParam HashMap<String,String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int page = Integer.parseInt(params.get("page"));
		
		int cnt = iTestMAService.getM1Cnt(params);
		
		PagingBean pb = iPagingService.getPagingBean(page, cnt, 5, 3);
		
		params.put("startCnt", Integer.toString(pb.getStartCount()));
		params.put("endCnt", Integer.toString(pb.getEndCount()));
		
		List<HashMap<String,String>> list = iTestMAService.getM1List(params);
		
		modelMap.put("list", list);
		modelMap.put("pb", pb);
		
		return mapper.writeValueAsString(modelMap);
	}
	
	@RequestMapping(value = "/testAMAdd")
	public ModelAndView testAMAdd(ModelAndView mav) {
		mav.setViewName("testa/testAMAdd");
		return mav;
	}
	
	
	
  @RequestMapping(value="/testAMCUDAjax", method= RequestMethod.POST,
	         produces = "text/json;charset=UTF-8" )
   @ResponseBody 
   public String testAMCUDAjax(@RequestParam HashMap<String, String> params) throws Throwable {
      
      ObjectMapper mapper = new ObjectMapper();
      
      Map<String, Object> modelMap = new HashMap<String, Object>();
      
      String result = "success";
      
      try {
         int cnt= 0;
         
         //CUD 처리
         
         switch(params.get("gbn")) {
         case "c": 
        	 //비밀번호 암호화
        	 params.put("pw", Utils.encryptAES128(params.get("pw")));
        	 
        	 cnt = iTestMAService.addM1(params);
        	 break;
         case "u": 
        	 //비밀번호 암호화
        	 params.put("pw", Utils.encryptAES128(params.get("pw")));
        	 cnt = iTestMAService.updateM1(params);
        	 break;
         case "d": 
        	 cnt = iTestMAService.deleteM1(params);
        	 break;
         }
         
         
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
  
	@RequestMapping(value = "/testAMIdCheckAjax", method=RequestMethod.POST,
	produces = "text/json; charset=UTF-8")
	@ResponseBody
	public String testAMIdCheckAjax(@RequestParam HashMap<String,String> params) throws Throwable{
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int cnt = iTestMAService.getM1IdCheck(params);
		modelMap.put("cnt", cnt);
		return mapper.writeValueAsString(modelMap);
	}
	
	@RequestMapping(value = "/testAM")
	public ModelAndView testAM(@RequestParam HashMap<String, String> params,
							ModelAndView mav) throws Throwable {		
		HashMap<String, String> data = iTestMAService.getM1(params);
		
		mav.addObject("data", data);
		
		mav.setViewName("testa/testAM");
		
		return mav;
	}
	
	
	@RequestMapping(value = "/testAMUpdate")
	public ModelAndView testAMUpdate(@RequestParam HashMap<String, String> params,
			ModelAndView mav) throws Throwable {		
		HashMap<String, String> data = iTestMAService.getM1(params);
		// 비밀번호 복호화
		data.put("M_PW", Utils.decryptAES128(data.get("M_PW")));
		
		mav.addObject("data", data);
		mav.setViewName("testa/testAMUpdate");
		
		return mav;
	}
}
