package com.spring.sample.web.testa.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestADao implements ITestADao{

	@Autowired
	public SqlSession sqlSession;
	
	@Override
	public int getABCnt(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("testA.getABCnt", params);
	}

	@Override
	public List<HashMap<String, String>> getABList(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.selectList("testA.getABList",params) ;
	}

	@Override
	public int abAdd(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.insert("testA.abAdd", params);
	}

	@Override
	public HashMap<String, String> getAB(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("testA.getAB",params);
	}

	@Override
	public int abUpdate(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.update("testA.abUpdate",params);
	}

	@Override
	public int abDelete(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.update("testA.abDelete");
	}

	@Override
	public void updateABHit(HashMap<String, String> params) throws Throwable {
		sqlSession.update("testA.updateABHit",params);
		
	}

}
