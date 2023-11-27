package com.test.persistence;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

//log message를 전용으로 찍는 도구
@Log4j
public class JDBCTest {

	@Test
	public void testConnection() {
		
		//JDBC 연결 확인
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "java1234");

//			Connection conn = null;
			
			assertNotNull(conn); //이게 Not Null이면 합격, Null이면 불합격!
			
			System.out.println(conn.isClosed()); //업무 메시지가 아니라 확인 메시지(log)
			
			//아래 3개가 println보다 필터링으로 종류별로 log 확인도 가능하여 더 편하다!!!
			log.info(conn.isClosed());
			log.warn("경고 메시지");
			log.error("에러 메시지");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
