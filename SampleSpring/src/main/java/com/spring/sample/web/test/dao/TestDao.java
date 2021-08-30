package com.spring.sample.web.test.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao implements ITestDao{
	@Autowired
	public SqlSession sqlSession;

	@Override
	public List<HashMap<String, String>> getB1List() throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
