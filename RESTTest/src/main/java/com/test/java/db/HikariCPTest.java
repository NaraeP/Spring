package com.test.java.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zaxxer.hikari.HikariDataSource;


//RunWith: Spring의 내용을 부르는 역할(?!)
//ContextConfiguration: 환경 설정 위치를 알려주는 역할
//아래 두개 어노테이션으로 저 root-context에 있는 bean파일을 호출할 수 있다.

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class HikariCPTest {
	
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
