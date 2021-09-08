package com.spring.sample.web.test.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NTestDao implements INTestDao{
	@Autowired
	public SqlSession sqlSession;

	@Override
	public List<HashMap<String, String>> getNTestList(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.selectList("m.getMList",params);
	}

	@Override
	public HashMap<String, String> getNTest1(HashMap<String, String> params) throws Throwable {
		
		return sqlSession.selectOne("m.getM1", params);
	}

	@Override
	public int addM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.insert("m.addM1", params);
	}

	@Override
	public int deleteM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.delete("m.deleteM1",params);
	}

	@Override
	public int getM1Cnt(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("m.getM1Cnt",params);
	}

	@Override
	public int getM1IdCheck(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("m.getM1IdCheck",params);
	}
	
	
}
