package kr.or.ddit.enumpkg;

import kr.or.ddit.vo.FactorialVO;

public enum FactorialType {
	FACTORIAL('=',(single)->{
		int sum = 1;
		for(int i = 1; i <= single; i++) {
			sum = sum*i;
		}
		return sum;
	});
	
	private interface RealFactorial{
		public int factorial(int single);
	}
	
	private FactorialType(int sign, RealFactorial realFactorial) {
		this.sign = sign;
		this.realFactorial = realFactorial;
	}
	
	private RealFactorial realFactorial;
	private int sign;
	public int getSign() {
		return sign;
	}
	
	public int factorial(int single) {
		return realFactorial.factorial(single);
	}
	private static final String EXPRPTRN = "!%d = %d";
	public String expression(FactorialVO vo) {
		return String.format(EXPRPTRN, vo.getSingle(),  factorial(vo.getSingle()));
	}
}
