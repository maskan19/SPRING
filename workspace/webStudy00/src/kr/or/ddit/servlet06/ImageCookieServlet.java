package kr.or.ddit.servlet06;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//한글 주석
@WebServlet("/01/imageCookie.do")
public class ImageCookieServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imageFilename = req.getParameter("image");
		if (imageFilename == null || imageFilename.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);/* 잘못된 접근입니다. */
			return;
		}
		String folder = "d:/contents";
		File imageFile = new File(folder, imageFilename);
		if (!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);/* 잘못된 접근입니다. */
			return;
		}
		
		String mime = getServletContext().getMimeType(imageFilename);
		if(mime==null ||!mime.startsWith("image/")) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);/* 잘못된 접근입니다. */
			return;
		}
		resp.setContentType(mime);
		
		String value = URLEncoder.encode(imageFilename, "UTF-8");
		Cookie imgCookie = new Cookie("imgCookie", imageFilename);   // 1
		imgCookie.setPath(req.getContextPath());
		imgCookie.setMaxAge(60*60*24*7);
		resp.addCookie(imgCookie);
		resp.setContentType(value);
		
		try (
		FileInputStream fis = new FileInputStream(imageFile);
		OutputStream os = resp.getOutputStream();
		){
		byte[] buffer = new byte[1024];
		int pointer = -1;
		while ((pointer = fis.read(buffer)) != -1) {
			os.write(buffer, 0, pointer);
		}
		};

		// req.setCharacterEncoding("utf-8");
		// resp.setContentType("image/jpg; charset=utf-8");

		// byte[] by = new byte[1024];
		// ServletOutputStream out = resp.getOutputStream();
		// String imagePath = getServletContext().getRealPath("")+"images\ccc.jpg";
		// String imagePath = "d:/contents/ccc.jpg";
		// BufferedInputStream in = new BufferedInputStream(new
		// FileInputStream(imagePath));

		// while(in.read(by) != 0) {
		// out.write(by);
		// }

		// in.close();
		// out.close();
	}
}