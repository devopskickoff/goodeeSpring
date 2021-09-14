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
import com.spring.sample.web.testa.service.ITestAService;

@Controller
public class TestAController {

	@Autowired
	public ITestAService iTestAService;
	
	@Autowired
	public IPagingService iPagingService;
	
	@RequestMapping(value = "/testABList")
	public ModelAndView testABList(@RequestParam HashMap<String,String> params, 
									ModelAndView mav) {
		
		String page = "1";
		
		if(params.get("page")!=null) {
			page = params.get("page");
		}
		mav.addObject("page",page);
		mav.setViewName("testa/testABList");
		return mav;
	}
	
/*	@RequestMapping
	value: 주소
	method: 전송방식
	produces: 돌려주는 형식
*/
	
	@RequestMapping(value = "/testABLists", method=RequestMethod.POST,
					produces = "text/json;charset=UTF-8")
	@ResponseBody // Spring에게 돌려주는 내용이 View임을 제시, spring에서 컨트롤러를 거치면 view 리졸버를 건들여야하는데 이건 건들이지 않겠다는 뜻 
	public String testABLists(@RequestParam HashMap<String,String> params) throws Throwable {
		ObjectMapper mapper = new ObjectMapper(); //jackson 객체
		Map<String, Object> modelMap = new HashMap<String, Object>(); //데이터를 담을 map을 선언
		
		//페이지 취득
		int page = Integer.parseInt(params.get("page"));
		
		//개수 취득
		int cnt = iTestAService.getABCnt(params);
		
		//페이징 정보 취득
		PagingBean pb = iPagingService.getPagingBean(page, cnt, 5,2);
		//데이터 시작, 종료값 할당
		params.put("startCnt", Integer.toString(pb.getStartCount()));
		params.put("endCnt", Integer.toString(pb.getEndCount()));
		//리스트 조회 
		List<HashMap<String,String>> list = iTestAService.getABList(params);
		//데이터 담기
		modelMap.put("list", list);
		modelMap.put("pb",pb);
		//데이터 문자열화
		
		return mapper.writeValueAsString(modelMap);
		//{list:[{B_NO:~, ~:~, ......}],{},...], pb: {startCnt:~, ...}}
	}
	
}
