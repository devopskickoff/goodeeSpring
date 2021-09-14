package com.spring.sample.web.testa.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;


public interface ITestADao {

	public int getABCnt(HashMap<String, String> params) throws Throwable;

	public List<HashMap<String, String>> getABList(HashMap<String, String> params) throws Throwable ;


}
