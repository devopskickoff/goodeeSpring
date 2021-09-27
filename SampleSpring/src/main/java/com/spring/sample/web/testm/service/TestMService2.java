package com.spring.sample.web.testm.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sample.web.test.dao.PSWITestODao;
import com.spring.sample.web.testa.dao.ITestADao;
import com.spring.sample.web.testm.dao.ITestMDao2;

@Service
public class TestMService2 implements ITestMService2{
	@Autowired
	public ITestMDao2 iTestMDao;

	@Override
	public int getMCnt(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMDao.getMCnt(params);
	}

	@Override
	public List<HashMap<String, String>> getMList(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMDao.getMList(params);
	}

	@Override
	public int addM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMDao.addM1(params);
	}

	@Override
	public HashMap<String, String> getM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMDao.getM1(params);
	}

	@Override
	public int deleteM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMDao.deleteM1(params);
	}

	@Override
	public int updateM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMDao.updateM1(params);
	}



}
