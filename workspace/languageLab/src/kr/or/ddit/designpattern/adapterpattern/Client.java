package kr.or.ddit.designpattern.adapterpattern;

public class Client {

	private Target target;//생성자와 setter로 설정 가능

	public Client(Target target) {
		super();
		this.target = target;
	}
	
	public void execute() {
		target.request();
	}
	
	public static void main(String[] args) {
//		Target target = new OtherConcrete();//kr.or.ddit.designpattern.adapterpattern.OtherConcrete에 의해 직접 처리된 명령 request
		
		Adaptee adaptee = new Adaptee();
		Target target = new Adapter(adaptee);//adaptee에 의해 완전히 임의로 처리된 명령
		Client client = new Client(target);
		
		client.execute();
		
		
	}
	
}
