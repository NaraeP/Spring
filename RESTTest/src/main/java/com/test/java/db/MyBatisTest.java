package com.test.java.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zaxxer.hikari.HikariDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MyBatisTest {

	//쿼리를 날릴려면 SqlSessionTemplate 가 필요한데 그게 또 root-context에 있다.
	//따라서 HikariCPTest와 동일하게 RunWith, ContextConfiguration 어노테이션 사용
	
	@Autowired
	SqlSessionTemplate template;
	
	@Test
	public void testQuery() {
		
		int count = template.selectOne("rest.test");
		
		assertNotNull(template);
		assertEquals(12, count); //현재 나의 tblAddress 테이블의 레코드가 12개여서
	}
	
	
	//테스트 전체 복사해서 가져오기(Test History 이력 증가 가능)
	@Test
	public void testConnection() {
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "java1234");
			
			assertNotNull(conn); //true: 검사 PASS, false: 검사 NON-PASS
			
			assertEquals("DB 연결", false, conn.isClosed()); //기댓값과 실제값 비교
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	private HikariDataSource dataSource;

	@Test
	public void testConnectionPool() {
		
		assertNotNull(dataSource);
		
		try {
			Connection conn = dataSource.getConnection();
			assertEquals(false, conn.isClosed());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
