package com.spring.sample.web.testa.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sample.web.testa.dao.ITestAMDao;

@Service
public class TestAMService implements ITestAMService{
	@Autowired
	public ITestAMDao iTestMADao;
	@Override
	public int getM1Cnt(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMADao.getM1Cnt(params);
	}
	@Override
	public List<HashMap<String, String>> getM1List(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMADao.getM1List(params);
	}
	@Override
	public int addM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMADao.addM1(params);
	}
	@Override
	public int updateM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMADao.updateM1(params);
	}
	@Override
	public int deleteM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMADao.deleteM1(params);
	}
	@Override
	public int getM1IdCheck(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMADao.getM1IdCheck(params);
	}
	@Override
	public HashMap<String, String> getM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestMADao.getM1(params);
	}
	
}
