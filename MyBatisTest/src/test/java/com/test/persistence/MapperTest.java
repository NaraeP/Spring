package com.test.persistence;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MapperTest {

	@Autowired //Autowired 해줘야 저 sqlSessionFactory 안에 뭐가 하나 들어간다.
	private SqlSessionFactory sqlSessionFactory;
	//factory bean의 부모
	
	
	@Test
	public void testQuery() {
		
		assertNotNull(sqlSessionFactory);
		
		//sqlSessionTemplate > SQL 실행(Statement 역할)
		SqlSession session = sqlSessionFactory.openSession();
		//얘가 있어야 쿼리를 날릴 수 있다.
		
		//session.executeQuery("select")
		
		String time = session.selectOne("test.time"); //namespace.id
		log.info(time);
		
		
		
	}
}
