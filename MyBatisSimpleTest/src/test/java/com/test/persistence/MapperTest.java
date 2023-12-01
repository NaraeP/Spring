package com.test.persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.mapper.TestMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTest {

	//@Autowired
	//private SqlSessionTemplate template;
	//위의 역할을 인터페이스가 대신한다.
	
	@Autowired
	private TestMapper mapper;
	
	@Test
	public void testMapper() {
		
		assertNotNull(mapper);
		
		//객체는 Interface를 상속받는 자식 클래스중의 하나인 오브젝트일 것이다.
		//어? Interface는 객체를 못만들고
		//Interface를 상속받는 자식 클래스를 만든 적이 없는데?
		//그럼에도 불구하고 InterfaceMapper는 스프링이 알아서 해당 인터페이스를 상속받은 자식 클래스를 만들어준다.
		//따라서 우리가 할 일은 자식클래스를 별도로 생성하지 않고 인터페이스 생성에서 끝난다!
		
		System.out.println(mapper.getTime());
		
		System.out.println(mapper.getTime2());
	}
}
