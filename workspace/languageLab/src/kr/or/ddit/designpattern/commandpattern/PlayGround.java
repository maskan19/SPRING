package kr.or.ddit.designpattern.commandpattern;

public class PlayGround {
	public static void main(String[] args) {
		Receiver1 recv1 = new Receiver1();
		Receiver2 recv2 = new Receiver2();
		Invoker1NoPattern iv1 = new Invoker1NoPattern(recv1);
		
		iv1.order();
		
		Invoker2NoPattern iv2 = new Invoker2NoPattern(recv2);
		
		iv2.order();
		
		// command pattern 적용
//		Command command1 = new ConcreteCommand(recv1);
//		Invoker front = new Invoker(command1);
//		front.order(); //리시버 1이 수행됨
//		
//		Command command2 = new ConcreteCommand2(recv2);
//		front.setCommand(command2);
//		front.order(); //리시버 2이 수행됨
		
		Command command1 = new ConcreteCommand(recv1);
		Command command2 = new ConcreteCommand2(recv2);
		Invoker front = new Invoker(command1, command2);
		front.order(0); //리시버 1이 수행됨
		front.order(1); //리시버 2이 수행됨
		front.order(7); //else문에 걸림
		
	}

}
