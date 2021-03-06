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

//한글 주석
@WebServlet("/01/imageForm.tmpl")
public class ImageFormServlet extends AbstractUseTmplServlet {

	@Override
	protected void setContentType(HttpServletResponse resp) {
		// TODO Auto-generated method stub
		resp.setContentType("text/html; charset=UTF-8");
	}

	@Override
	protected void makeData(HttpServletRequest req) {
		// TODO Auto-generated method stub
		System.out.println("서블릿이 요청 받았음");
		String folder = "d:/contents";
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

//	
////	private ServletContext application;
////
////	@Override
////	public void init(ServletConfig config) throws ServletException {
////		// TODO Auto-generated method stub
////		super.init(config);
////		application = config.getServletContext();
////	}
//
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		System.out.println("서블릿이 요청 받았음");
//		resp.setContentType("text/html; charset=UTF-8");
//		String folder = "d:/contents";
//		File contents = new File(folder);
//		String[] children = contents.list(new FilenameFilter() {
//
//			@Override
//			public boolean accept(File dir, String name) {
//				String mime = application.getMimeType(name);
//				return mime != null && mime.startsWith("image/");
//			}
//		});
//
////		StringBuffer html = new StringBuffer("<     html><body>");
////		String[] images = {"gif", "png", "jpg", "jpeg"};
//
////		html.append("<form action='image.do'><select name='image'>");
//		
////		String tmplPath = req.getServletPath();
////		InputStream is = application.getResourceAsStream(tmplPath);
////		InputStreamReader isr = new InputStreamReader(is); //젠더. 변환용
////		BufferedReader reader = new BufferedReader(isr);
////		String temp = null;
////		StringBuffer tmplSrc = new StringBuffer();
////		while((temp = reader.readLine())!=null) {
////			tmplSrc.append(String.format("%s\n", temp));
////		}
//
//		Date today = new Date();
//		StringBuffer options = new StringBuffer();
//		
//		for (String child : children) {
////			String mime = getServletContext().getMimeType(child);
////			for(int i = 0; i<images.length; i++) {
////			if(mime.startsWith("image/"))
//			options.append(String.format("<option>%s</option>", child));
//			}
//		String html = tmplSrc.toString().replace("%options%", options.toString()).replace("%today%", today.toString());
////		}
////		html.append("</select><input type='submit' value='전송'></input></form></body></html>");
//		// PrintWriter out = resp.getWriter();
//		// out.println(html);
//		
////		StringBuffer html = tmplSrc;
//		
//		
//		PrintWriter out = null;
//		try {
//			out = resp.getWriter();
//			out.print(html);
//		} catch (Exception e) {
//		} finally {
//			if (out != null)
//				out.close();
//		}
//	}

}