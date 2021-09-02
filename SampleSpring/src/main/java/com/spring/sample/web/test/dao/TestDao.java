package com.spring.sample.web.test.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao implements ITestDao {
	@Autowired
	public SqlSession sqlSession;

	@Override
	public List<HashMap<String, String>> getB1List(HashMap<String,String> params) throws Throwable {
		// selectList("네임스페이스.아이디") : 해당 select를 호출한다
		return sqlSession.selectList("test.getB1List",params);
	}

	@Override
	public HashMap<String, String> getB1(HashMap<String, String> params) throws Throwable {
		return sqlSession.selectOne("test.getB1", params);
	}

	@Override
	public int addB1(HashMap<String, String> params) throws Throwable {
		return sqlSession.insert("test.addB1",params);
	}

	@Override
	public int updateB1(HashMap<String, String> params) throws Throwable {
		return sqlSession.update("test.updateB1",params);
	}

	@Override
	public int deleteB1(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.delete("test.deleteB1",params);
	}

	@Override
	public int getB1Cnt(HashMap<String, String> params) throws Throwable {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("test.getB1Cnt",params);
	}

}
