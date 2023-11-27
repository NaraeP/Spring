package com.test.spring.di02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		//Spring DI 구현
		
		//스프링 설정(취향 차이)
		//1. XML 설정(Old)
		//2. 어노테이션 설정
		//3. Java 설정(New)
		
		//스프링 프레임워크 특징
		//- 필요한 객체를 생성 ~ 소멸 할때까지의 과정을 스프링이 관리해준다.
		
		//Pen 객체 생성하기
		Pen p1 = new Pen();
		p1.write();
		
		//Pen 객체 > 스프링을 통해서 생성하기 > XML 방식
		
		//스프링 설정 파일 읽기
//		ApplicationContext context = new ClassPathXmlApplicationContext("XML 파일의 경로");
//		ApplicationContext context = new ClassPathXmlApplicationContext("file:/src/main/java/com/test/di02/di02.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("./com/test/spring/di02/di02.xml");
		
		//빈을 1개 주세요~ > 객체를 1개 주세요 ~ 객체를 1개 생성해주세요!!
		Pen p2 = (Pen)context.getBean("pen"); //name 기록
		p2.write();
		
		Brush b1 = new Brush();
		b1.draw();
		
		Brush b2 = (Brush)context.getBean("brush"); //id
		b2. draw();
				
		Brush b3 = (Brush)context.getBean("b1"); //name
		b3.draw();
		
		Brush b4 = (Brush)context.getBean("myBrush"); //name
		b4.draw();
		
		//---------------------
		
		//Main > (의존) > Hong > (의존) > Pen
		
		//Main > (의존) > Park > Choi > Brush
		
//		Hong hong = new Hong(펜);
//		hong.run();
		
		
		Hong hong = (Hong)context.getBean("hong");
		hong.run();
		
		//이전에 우리가 사용했던 방식은 객체간의 관계가 전역이 아니라 각자 관리 > 필요시 해당 클래스에서 생성자 만들고 해당 메서드 사용하는 방식
		
		//의존 주입의 특징
		//- 객체들의 모든 관계가 한 곳에서 관리(중앙 집중 관리형)
		
		
		//[스프링 사용 X]
		
		Brush brush1 = new Brush();
		
		Choi choi1 = new Choi();
		choi1.setBrush(brush1); //의존 주입 > Setter
		
		Park park1 = new Park(choi1); //의존 주입 > 생성자
		
		park1.run();
		//특징: 설정이 한 곳에 몰린다.
		
		//[스프링 사용 O] > 객체간의 의존 관계 정의 > XML 설정
		
		Park park2 = (Park)context.getBean("park");
		park2.run();
		
		//특징: 족보는 xml에서 만들어두면 java에서는 신경쓸 필요없이 직접적인 의존 관계인 대상 1개만 객체를 만들면 된다!
		
	
		
		
	}
}
