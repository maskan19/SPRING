package kr.or.ddit.enumpkg;

import kr.or.ddit.vo.CalculatorVO;

public enum OperatorType {
	PLUS('+', new RealOperator() {
		public double operate(double left, double right) {
			return left + right;
		}
	}), MINUS('-', new RealOperator() {
		public double operate(double left, double right) {
			return left - right;
		}
	}), MULTIPLY('*', new RealOperator() {
		public double operate(double left, double right) {
			return left * right;
		}
	}), DIVIDE('/', (left, right) -> {
		return left / right;
	}), MODULAR('%', (left, right) -> {
		return left % right;
	});

	private RealOperator realOperator;
	private char sign;

	private OperatorType(char sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}

	public char getSign() {
		return sign;
	}

	@FunctionalInterface
	private interface RealOperator {
		double operate(double left, double right); // 접근 방법만 제시
	}

	public double operate(double left, double right) {
		return realOperator.operate(left, right);
	}

	private static final String EXPRPTRN = "%f %s %f = %f";

	public String expression(CalculatorVO vo) {
		return String.format(EXPRPTRN, vo.getLeft(), sign, vo.getRight(), operate(vo.getLeft(), vo.getRight()));
	}
}
