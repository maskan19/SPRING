package kr.or.ddit.servlet03.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class XmlView extends AbstractView {

	@Override
	public void mergeModelAndView(Object target, HttpServletResponse resp) throws IOException {
		try { // overide에
			JAXBContext context = JAXBContext.newInstance(target.getClass());
			Marshaller marchaller = context.createMarshaller();

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException(); // 발생한 익셉션을 변경하는 과정
		}

	}

}
