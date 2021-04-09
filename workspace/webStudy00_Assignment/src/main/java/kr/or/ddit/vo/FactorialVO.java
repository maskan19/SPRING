package kr.or.ddit.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import kr.or.ddit.enumpkg.FactorialType;


@XmlRootElement(name="data")
@XmlAccessorType(XmlAccessType.FIELD)
public class FactorialVO implements Serializable {
	public FactorialVO() {
		super();
	}
	
	public FactorialVO(int single, FactorialType factorial) {
		super();
		this.single = single;
		this.factorial = factorial;
	}

	private int single;
	private String expression;
	private FactorialType factorial;
	
	
	public FactorialType getFactorial() {
		return factorial;
	}

	public void setFactorial(FactorialType factorial) {
		this.factorial = factorial;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public int getSingle() {
		return single;
	}

	public void setSingle(int single) {
		this.single = single;
	}
	
	
}
