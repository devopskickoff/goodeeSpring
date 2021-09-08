package com.spring.sample.web.test.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestMDao implements ITestMDao {
	@Autowired
	public SqlSession sqlSession;

	@Override
	public int getM1Cnt(HashMap<String, String> params) throws Throwable {
		return sqlSession.selectOne("testM.getM1Cnt", params);
	}

	@Override
	public List<HashMap<String, String>> getM1List(HashMap<String, String> params) throws Throwable {
		return sqlSession.selectList("testM.getM1List", params);
	}

	@Override
	public int getM1IdCheck(HashMap<String, String> params) throws Throwable {
		return sqlSession.selectOne("testM.getM1IdCheck", params);
	}

	@Override
	public int addM1(HashMap<String, String> params) throws Throwable {
		return sqlSession.insert("testM.addM1", params);
	}

	@Override
	public HashMap<String, String> getM1(HashMap<String, String> params) throws Throwable {
		return sqlSession.selectOne("testM.getM1", params);
	}

	@Override
	public int updateM1(HashMap<String, String> params) throws Throwable {
		return sqlSession.update("testM.updateM1", params);
	}

	@Override
	public int deleteM1(HashMap<String, String> params) throws Throwable {
		return sqlSession.delete("testM.deleteM1", params);
	}

	@Override
	public HashMap<String, String> getM1Login(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("testM.getM1Login", params);
	}
}

