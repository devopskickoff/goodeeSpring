package com.spring.sample.web.testa.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sample.web.test.dao.PSWITestODao;
import com.spring.sample.web.testa.dao.ITestADao;

@Service
public class TestAService implements ITestAService{
	@Autowired
	public ITestADao iTestADao;

	@Override
	public int getABCnt(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestADao.getABCnt(params);
	}

	@Override
	public List<HashMap<String, String>> getABList(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestADao.getABList(params);
	}
}
