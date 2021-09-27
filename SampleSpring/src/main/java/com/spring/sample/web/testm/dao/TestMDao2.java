package com.spring.sample.web.testm.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestMDao2 implements ITestMDao2{

	@Autowired
	public SqlSession sqlSession;

	@Override
	public int getMCnt(HashMap<String, String> params) throws Throwable {
		return sqlSession.selectOne("testM2.getM1Cnt",params);
	}

	@Override
	public List<HashMap<String, String>> getMList(HashMap<String, String> params) throws Throwable {
		return sqlSession.selectList("testM2.getM1List",params);
	}

	@Override
	public int addM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.insert("testM2.addM1", params);
	}

	@Override
	public HashMap<String, String> getM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("testM2.getM1", params);
	}

	@Override
	public int deleteM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.update("testM2.deleteM1",params);
	}

	@Override
	public int updateM1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.update("testM2.updateM1",params);
	}

	
	

}
