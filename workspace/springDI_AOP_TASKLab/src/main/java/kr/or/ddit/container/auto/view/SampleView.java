package kr.or.ddit.container.auto.view;

import kr.or.ddit.container.auto.service.ISampleService;

public class SampleView {
	
	private ISampleService service;
	public void view() {
		System.out.println(service.ReadData("a001"));
	}
	
	public void setService(ISampleService service) {
		this.service = service;
	}
	
	public static void main(String[] args) {
		
	}
	
}
