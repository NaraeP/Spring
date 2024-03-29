package com.test.spring.di01;

public class Lee {

	//Lee > (의존) > Brush
	
	//의존 관계 형성 방법
	//1. 기존 방식
	//2. DI 구현
	
	//DI 구현 > 의존 주입 도구 필요(***)
	//1. 생성자
	// - 딱 1번만 호출 가능
	// - 안전성 높음
	//2. Setter
	// - 언제든 호출 가능
	
	
	private Brush brush;
	
	//의존 주입 도구
	//> 의존 객체를 스스로 생성하지 않고, 외부로부터 건내받는 도구
	public Lee(Brush brush) { //의존 주입 발생
		this.brush = brush;
	}
	
	public void setBrush(Brush brush) {
		this.brush = brush;
	}
	
	public void run() {

		//Brush brush = new Brush();
		brush.draw();
		
		
	}


}
