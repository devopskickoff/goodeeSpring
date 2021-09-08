package com.spring.sample.web.test.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.sample.web.test.dao.INTestDao;

@Service
public class NTestService implements INTestService{

	@Autowired 
	public INTestDao iNTestDao;
	@Override
	public List<HashMap<String, String>> getNTestList(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iNTestDao.getNTestList(params);
	}
	@Override
	public HashMap<String, String> getNtest1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iNTestDao.getNTest1(params);
	}
	@Override
	public int addM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iNTestDao.addM1(params);
	}
	@Override
	public int deleteM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iNTestDao.deleteM1(params);
	}
	@Override
	public int getM1Cnt(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iNTestDao.getM1Cnt(params);
	}
	@Override
	public int getM1IdCheck(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return iNTestDao.getM1IdCheck(params);
	}
	
}
