package com.spring.sample.web.testa.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestAMDao implements ITestAMDao{
	@Autowired
	public SqlSession sqlSession;

	@Override
	public int getM1Cnt(HashMap<String, String> params) throws Throwable {
		return sqlSession.selectOne("testMA.getM1Cnt",params);
	}

	@Override
	public List<HashMap<String, String>> getM1List(HashMap<String, String> params) throws Throwable {
		return sqlSession.selectList("testMA.getM1List",params);
	}

	@Override
	public int addM1(HashMap<String, String> params) throws Throwable {
		return sqlSession.insert("testMA.addM1",params);
	}

	@Override
	public int updateM1(HashMap<String, String> params) throws Throwable {
		return sqlSession.update("testMA.updateM1",params);
	}

	@Override
	public int deleteM1(HashMap<String, String> params) throws Throwable {
		return sqlSession.update("testMA.deleteM1",params);
	}

	@Override
	public int getM1IdCheck(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("testMA.getM1IdCheck",params);
	}

	@Override
	public HashMap<String, String> getM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("testMA.getM1",params);
	}
}
