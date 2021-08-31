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

}
