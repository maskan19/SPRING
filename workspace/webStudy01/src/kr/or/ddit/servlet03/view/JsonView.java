package kr.or.ddit.servlet03.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonView extends AbstractView {
	// 마샬링과 직렬화 작업을 JsonView 클래스가 담당하기 때문에 
	// Model2 방식이 된다. (즉, 책임분리가 됨)
	@Override
	public void mergeModelAndView(Object target, HttpServletResponse resp) throws IOException {
		// gson 이랑 같은 기능
		ObjectMapper mapper = new ObjectMapper();
//		String json =  mapper.writeValueAsString(target);
		try(
			PrintWriter out = resp.getWriter();
		) {
			//직렬화와 마샬링 작업을 한꺼번에 수행해주게 해줌
			mapper.writeValue(out, target);
//			out.print(json);
		}
	}
}
