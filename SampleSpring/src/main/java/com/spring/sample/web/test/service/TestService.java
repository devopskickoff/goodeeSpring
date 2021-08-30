package com.spring.sample.web.test.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sample.web.test.dao.ITestDao;

@Service
public class TestService implements ITestService{
	@Autowired
	public ITestDao iTestDao;

	@Override
	public List<HashMap<String, String>> getB1List() throws Throwable {
		// TODO Auto-generated method stub
		return iTestDao.getB1List();
	}
}
