package com.spring.sample.web.testm.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;


public interface ITestMDao2 {


	public int getMCnt(HashMap<String, String> params) throws Throwable;

	public List<HashMap<String, String>> getMList(HashMap<String, String> params) throws Throwable;

	public int addM1(HashMap<String, String> params) throws Throwable;

	public HashMap<String, String> getM1(HashMap<String, String> params) throws Throwable;

	public int deleteM1(HashMap<String, String> params) throws Throwable;

	public int updateM1(HashMap<String, String> params) throws Throwable;


}
