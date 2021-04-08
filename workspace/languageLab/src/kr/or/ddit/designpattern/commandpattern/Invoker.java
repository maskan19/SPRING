package kr.or.ddit.designpattern.commandpattern;

public class Invoker {

	private Command[] commands; //구현체인 ConcreteCommand가 와도 상관없다

//	public void setCommand(Command command) {
//		this.commands = command;
//	}

	public Invoker(Command...command) {
		super();
		this.commands = command;
	}
	
	public void order(int mapping) {
		if(commands.length>mapping)
		commands[mapping].execute(); //어떤 구현체가 실행될지는 신경쓰지 않음
		else
			throw new RuntimeException("404");
	}
	
}