package com.spring.test.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

//@Service : 기능들을 모아놓은 클래스 
@Service
public class TestService implements ITestService{

	@Override
	public void test() {
		System.out.println("서비스 메소드 호출됨");
		
	}

	@Override
	public List<String> gugudan(int num) {
		List<String> list = new ArrayList<String>();
		for(int i=1;i<=9;i++) {
			list.add(num+"*"+i+"="+num*i);
		}
		return list;
	}
	
	
}
