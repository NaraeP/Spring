package com.test.persistence;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.domain.MyBatisDTO;

@Repository
public class MyBatisDAOImpl implements MyBatisDAO {

	@Autowired
	private SqlSessionTemplate template;
	
	@Override
	public void test() {

		System.out.println(template == null);
		
	}

	@Override
	public int add(MyBatisDTO dto) {

		/*
			JDBC
			1. Connection
			2. Statement
			3. ResultSet
			
			MyBatis
			1. SqlSessionTemplate
				a. 반환값X (INSERT, UPDATE, DELETE)
					- stat.executeUpdate() (久)
					- template.insert() (新)
					- template.update() (新)
					- template.delete() (新)
				
				b. 반환값O (SELECT)
					- stat.executeQuery() (久)
					- template.selectOne() (新) > 결과셋 레코드 1개
					- template.selectList() (新) > 결과셋 레코드 N개
		*/
		
		
		//this.template.insert("mapper's namespace.쿼리 id", 인자값)
		return this.template.insert("mybatis.add", dto);
	}

	@Override
	public void m1() {
		
		//update tblMyBatis set age = age + 1;
		int result = this.template.update("mybatis.m1");
		
		System.out.println("적용된 행의 개수: " + result);
		
	}

	@Override
	public int m2(String seq) {

		return this.template.delete("mybatis.m2", seq);
		//int, String 등 넘어갈게 다양하니 object로 매개변수를 넘겨주도록 오버로딩 되어있다.
		//기존 JDBC의 try catch문에 들어가는 과정이 위의 return문 하나로 퉁친다! Thank you ~ so easy ~
	}

	@Override
	public int m3(MyBatisDTO dto) {

		return this.template.insert("mybatis.m3", dto);
		//Object 타입의 매개변수를 최대 1개밖에 넣지 못한다. > 때문에 보낼 값이 1개가 되었든, 여러개가 되었든 어딘가에 담아서, 포장(DTO)해서 보내야 한다.
		//아까는 단일값. 지금은 복합값(클래스 객체)
	}

	@Override
	public int m4(HashMap<String, String> map) {
		
		return this.template.update("mybatis.m4", map);
	}

	@Override
	public int m5() {

		/*
			JDBC의 경우,
			
			String sql = "";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				count = rs.getInt("count");
			}
			
			return count;
		
			=> 더이상 위와 같이 할 필요가 없다. 아래 return문 한 줄로 해결! So easssssssssy~~~~~~~~~~~
		*/
		
		
		return this.template.selectOne("mybatis.m5");
	}

	@Override
	public MyBatisDTO m6(String seq) {

		/*
			JDBC의 경우,
			
			String sql = "";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			MyBatisDTO dto = new MyBatisDTO();
			
			if (rs.next()) {
				
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getString("age"));
				..
			}
			
			return dto;
		
			=> 더이상 위와 같이 할 필요가 없다. 아래 return문 한 줄로 해결! So easssssssssy~~~~~~~~~~~
		 */
		
		return this.template.selectOne("mybatis.m6", seq);
	}

	@Override
	public List<String> m7() {

		/*
			JDBC의 경우,
			
			String sql = "";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			List<String> names = new ArrayList<String>();
			
			
			while (rs.next()) {
				names.add(rs.getString("name"));
			}
			
			return list;
		
			=> 더이상 위와 같이 할 필요가 없다. 아래 return문 한 줄로 해결! So easssssy~~~~~
		 */
		
		return this.template.selectList("mybatis.m7");
	}

	@Override
	public List<MyBatisDTO> m8() {

		/*
			JDBC의 경우,
			
			String sql = "";
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			List<MyBatisDTO> list = new ArrayList<MyBatisDTO>();
			
			while (rs.next()) {
				MyBatisDTO dto = new MyBatisDTO(); 
				dto.setSeq(rs.getString("seq"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getString("age"));
				..
				list.add(dto);
			}
			
			return list;
		
			=> 더이상 위와 같이 할 필요가 없다. 아래 return문 한 줄로 해결! So easssssy~~~~~
		 */
		
		return this.template.selectList("mybatis.m8");
	}

	@Override
	public int m9(String table) {

		return this.template.selectOne("mybatis.m9", table);
	}

	@Override
	public int m10() {

		return this.template.selectOne("mybatis.m10");
	}

	@Override
	public List<MyBatisDTO> m11(String word) {

		return this.template.selectList("mybatis.m11", word);
	}

	@Override
	public List<String> m12(String type) {

		return this.template.selectList("mybatis.m12", type);
	}

	@Override
	public List<MyBatisDTO> m13(HashMap<String, String> map) {

		return this.template.selectList("mybatis.m13", map);
	}
	

}
