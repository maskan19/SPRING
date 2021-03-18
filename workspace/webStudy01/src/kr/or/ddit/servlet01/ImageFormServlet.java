package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/01/imageForm.tmpl")
public class ImageFormServlet extends AbstractUseTmplServlet {
	
	@Override
	protected void setContextType(HttpServletResponse resp) {
		resp.setContentType("text/html; charset=UTF-8");		
	}
	
	@Override
	protected void makeDate(HttpServletRequest req) {
		System.out.println("서블릿 요청 받았음.");
		
		String folder = "D:\\contents\\images";
		File contents = new File(folder);
		String[] children = contents.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("image/");
			}
		});

		Date today = new Date();
		req.setAttribute("today", today);
		
		StringBuffer options = new StringBuffer();
		
		for (String child : children) {
			options.append(String.format("<option>%s</option>", child));
		}
		req.setAttribute("options", options);
	}

}