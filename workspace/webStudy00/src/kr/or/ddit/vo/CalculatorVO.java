package kr.or.ddit.vo;

import java.io.Serializable;

import kr.or.ddit.enumpkg.OperatorType;

public class CalculatorVO implements Serializable {

	public CalculatorVO() {
		super();
	}

	public CalculatorVO(double left, double right, OperatorType operator) {
		super();
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	private double left;
	private double right;
	private OperatorType operator;
	private double result;
	private String expression;

	public double getLeft() {
		return left;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public double getRight() {
		return right;
	}

	public void setRight(double right) {
		this.right = right;
	}

	public OperatorType getOperator() {
		return operator;
	}

	public void setOperator(OperatorType operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "CalculateVO [ left=" + left + ", right = " + right + ", operator = " + operator + " ]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(left);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		temp = Double.doubleToLongBits(right);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalculatorVO other = (CalculatorVO) obj;
		if (Double.doubleToLongBits(left) != Double.doubleToLongBits(other.left))
			return false;
		if (operator != other.operator)
			return false;
		if (Double.doubleToLongBits(right) != Double.doubleToLongBits(other.right))
			return false;
		return true;
	}

}
