package com.spring.sample.web.test.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sample.web.test.dao.PSWITestODao;

@Service
public class PSWTestOService implements PSWITestOService{

	@Autowired
	public PSWITestODao iTestDao;
	@Override
	public List<HashMap<String, String>> getO1List(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestDao.getO1List(params);
	}
	@Override
	public int updateO1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestDao.updateO1(params);
	}
	@Override
	public int deleteO1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestDao.deleteO1(params);
	}
	@Override
	public int addO1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestDao.addO1(params);
	}
	@Override
	public int getO1Cnt(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iTestDao.getO1Cnt(params);
	}

}
