package com.test.java.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class JDBCTest {

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
}
