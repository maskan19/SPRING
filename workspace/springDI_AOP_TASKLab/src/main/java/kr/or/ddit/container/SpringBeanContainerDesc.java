package kr.or.ddit.container;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.example.service.IExampleService;

/**
 * Spring Bean Container 사용단계 
 * : bean 의 lifecycle 관리자
 * 1. spring container module을 빌드패스에 추가
 * 		(beans, core, context spEL)
 * 2. bean meta data(bean definition metadata) 등록 파일
 * 		실제 객체에 대한 부가정보를 세팅하는 것
 * 		1) bean element 등록
 * 		2) 등록된 bean 간의 의존관계 형성(의존성 주입 dependency injection)
 * 			- constructor injection (필수 전략을 주입)
 * 				constructor-arg, c namespace(3.1)
 * 			- setter injection  (optional 전략 주입)
 * 				property, p namespace(3.0)
 * 3. entry point 에서 Container 객체 생성
 * 		ApplicationContext의 구현체
 * 4. getBean으로 의존 객체 주입
 * 		- type을 기준으로 한 주입은 두개 이상의 bean이 존재할 경우 exception 발생
 * 		- id를 기준으로 한 주입은 캐스팅이 필요
 * 		- id와 type을 주입하면 캐스팅 없이 특정 객체를 생성할 수 있음
 * 5. 컨테이너 종료(shutdownHook 등록)
 * 
 * **컨테이너의 빈 관리 정책
 * 1. 특별한 설정(scope)이 없는한 빈은 singleton으로 관리된다.
 *  	scope default(singleton) : 하나의 빈은 하나의 객체
 *  	scope prototype은 주입될 때마다 새로운 bean객체 생성
 *  	scope request은 웹 어플리케이션에서 request가 들어올 때 생성, response가 나갈 때 파기
 *  	scope session은 웹 어플리케이션에서 session이 생성될 때 생성, session가 만료될 때 파기
 *  2. 특별한 설정(lazy-init="true")이 없는 한 컨테이너가 초기화될 때 등록된 빈의 모든 객체를 생성한다.
 *  	객체의 생성 시점을 지연시키거나 생성 순서를 어느정도 제어할 수 있음.
 *  	default-lazy-init="true" 설정하면 디폴트값이 true로 바뀐다.
 * 3. depends-on 을 이용하여 빈들간의 순서를 직접 제어. 
 * 		depends-on="bean객체" 속성은 지목 bean이 생성된 이후에 생성된다.
 * 4. 생명주기 콜백 정의 가능
 * 		init-method="" destroy-method=""
 * 		default-init-method="메소드명"	default-destroy-method="메소드명"
 * 		*** init-method는 필요한 주입(injection)이 모두 끝난 이후에 호출된다.
 */
public class SpringBeanContainerDesc {
	public static void main(String[] args) {
		ConfigurableApplicationContext container 
		= new GenericXmlApplicationContext("classpath:kr/or/ddit/container/conf/spring-container.xml");//close가 가능한 객체
//		classpath: smartResource로 classpath 경로
//		file: file 경로
//		(address): 웹상 경로
		
		container.registerShutdownHook(); //메인 스레드가 사라지면 저절로 셧다운

//		IExampleService service1 = container.getBean("service1", IExampleService.class);
//		IExampleService service1_1 = container.getBean("service1", IExampleService.class);
//		IExampleService service2 = container.getBean("service2", IExampleService.class);
//		IExampleService service2_1 = container.getBean("service2", IExampleService.class);
//		System.out.println(service1.readData("a002"));
//		System.out.println(service1 == service2); //false. 다른 bean으로 생성됨
//		System.out.println(service1 == service1_1); //true. singleton으로 생성된 객체다.
//		System.out.println(service2 == service2_1); //false. prototype으로 생성된 객체다.
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
//		container.close();
	}
}
