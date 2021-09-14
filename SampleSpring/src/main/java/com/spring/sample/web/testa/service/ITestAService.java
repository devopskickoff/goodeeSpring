package com.spring.sample.web.testa.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;


public  interface ITestAService {

	public int getABCnt(HashMap<String, String> params) throws Throwable;

	public List<HashMap<String, String>> getABList(HashMap<String, String> params) throws Throwable;

}
