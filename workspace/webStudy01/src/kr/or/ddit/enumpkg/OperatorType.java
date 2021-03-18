package kr.or.ddit.enumpkg;

import kr.or.ddit.vo.CalculateVO;

// 책임 분리의 장점
public enum OperatorType {
   PLUS('+', new RealOperator() {
      public double operate(double left, double right) {
         return left + right;
      }
   }
   ), MINUS('-', new RealOperator() {
      public double operate(double left, double right) {
         return left - right;
      }
   }), MULTIPLY('*', new RealOperator() {
      public double operate(double left, double right) {
         return left * right;
      }
   }), DIVIDE('/', (left, right) -> { // FunctionalInterface 인 메소드일 때만 람다식 표현식을 사용 할 수 있다.
         return left / right;
   }), MODULAR('%', (left, right) -> { // FunctionalInterface 인 메소드일 때만 람다식 표현식을 사용 할 수 있다.
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
   
   // FunctionalInterface 라고 한다.
   // @FunctionalInterface 생략이 되어있다.
   private interface RealOperator{
	   // 객체가 생성 되기 전까지 어떤걸 처리 할 지 모르기 때문에 인터페이스로 정의해서 처리
      double operate(double left, double right);
   }
   
   public double operate(double left, double right) {
      return realOperator.operate(left, right);
   }
   
   private static final String EXPRPTRN = "%f %s %f = %f";
   public String expression(CalculateVO vo) {
	   return String.format(EXPRPTRN, vo.getLeft(), sign, vo.getRight(), operate(vo.getLeft(), vo.getRight()));
   }

}