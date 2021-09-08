package com.spring.sample.web.test.dao;

import java.util.HashMap;
import java.util.List;

public interface INTestDao {

	public List<HashMap<String, String>> getNTestList(HashMap<String, String> params) throws Throwable;

	public HashMap<String, String> getNTest1(HashMap<String, String> params) throws Throwable;

	public int addM1(HashMap<String, String> params) throws Throwable;

	public int deleteM1(HashMap<String, String> params) throws Throwable;

	public int getM1Cnt(HashMap<String, String> params) throws Throwable;

	public int getM1IdCheck(HashMap<String, String> params) throws Throwable;

}
