package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/01/textViewer.tmpl")
public class TextFormServlet extends AbstractUseTmplServlet {
	@Override
	protected void setContextType(HttpServletResponse resp) {
		resp.setContentType("text/html; charset=UTF-8");
	}

	@Override
	protected void makeDate(HttpServletRequest req) {
		System.out.println("서블릿이 요청을 받았음");
		
		String folder = TextFormServlet.class.getClassLoader().getResource("datas").toString();
		File contents = new File(folder.substring(folder.indexOf("D")));
		
		String[] children = contents.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("text/");
			}
		});
		
		StringBuffer options = new StringBuffer();
		
		for(String child : children){
			options.append(String.format("<option>%s</option>", child));
		}
		req.setAttribute("options", options);
	}

	
}