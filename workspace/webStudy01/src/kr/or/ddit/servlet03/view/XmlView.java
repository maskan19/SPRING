package kr.or.ddit.servlet03.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class XmlView extends AbstractView {

	@Override
	public void mergeModelAndView(Object target, HttpServletResponse resp) throws IOException {
		//respData.append(String.format("<result>%s</result>", expr));
		// throws 는 예외가 발생 했을 때 톰캣에게 넘겨 줌
		// throw 는 예외가 발생 했을 때 그냥 에러 넘저줌
		try (
			PrintWriter out = resp.getWriter();
		) {
			JAXBContext context = JAXBContext.newInstance(target.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(target, out);
		} catch (JAXBException e) {
			// IOException 으로 JAXBException 을 포장해서 톰캣에게 전달해줄 수 있게 해줌
			throw new IOException(e);
		}
		
	}

}
