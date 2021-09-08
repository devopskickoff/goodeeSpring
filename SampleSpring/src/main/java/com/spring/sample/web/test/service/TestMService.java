package com.spring.sample.web.test.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sample.web.test.dao.ITestMDao;

@Service
public class TestMService implements ITestMService {
	@Autowired
	public ITestMDao iTestMDao;

	@Override
	public int getM1Cnt(HashMap<String, String> params) throws Throwable {
		return iTestMDao.getM1Cnt(params);
	}

	@Override
	public List<HashMap<String, String>> getM1List(HashMap<String, String> params) throws Throwable {
		return iTestMDao.getM1List(params);
	}

	@Override
	public int getM1IdCheck(HashMap<String, String> params) throws Throwable {
		return iTestMDao.getM1IdCheck(params);
	}

	@Override
	public int addM1(HashMap<String, String> params) throws Throwable {
		return iTestMDao.addM1(params);
	}

	@Override
	public HashMap<String, String> getM1(HashMap<String, String> params) throws Throwable {
		return iTestMDao.getM1(params);
	}

	@Override
	public int updateM1(HashMap<String, String> params) throws Throwable {
		return iTestMDao.updateM1(params);
	}

	@Override
	public int deleteM1(HashMap<String, String> params) throws Throwable {
		return iTestMDao.deleteM1(params);
	}

	@Override
	public HashMap<String, String> getM1Login(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMDao.getM1Login(params);
	}
}








