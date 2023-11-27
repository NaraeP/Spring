package com.test.spring.di02;

public class Park {

	private Choi choi;
	
	public Park(Choi choi) {
		this.choi = choi;
	}
	
	public void run() {
		
		System.out.println("Park 업무 중..");
		
		//Choi choi = new Choi();
		//직접 구현 X > 스프링으로 구현!!!
		
		choi.doSomething();
	}
}
