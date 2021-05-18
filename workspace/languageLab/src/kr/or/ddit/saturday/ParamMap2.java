package kr.or.ddit.saturday;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Map 타입으로서 키는 반드시 String으로 처리 */
public class ParamMap2 extends HashMap<String, Object>{


	public static void main(String[] args) {
		
//		ParamMap param = new ParamMap();
//		param.put("key1", "value1");
//		System.out.println(param);
//		String value = param.getString("key1");
//		
		String key = "TEST_VAL1_VAL2";
		key = key.toLowerCase();
		Matcher matcher = Pattern.compile("(_)([a-z])([a-z0-9]+)").matcher(key);
		while(matcher.find()) {
			System.out.println(matcher.group());
		}
		
	}
}
