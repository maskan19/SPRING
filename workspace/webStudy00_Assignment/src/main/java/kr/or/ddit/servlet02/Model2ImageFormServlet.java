package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;

//@WebServlet("/02/imageForm.do")
@Controller
public class Model2ImageFormServlet {
	private ServletContext application;

	@RequestMapping(value = "/02/imageForm.do", method = RequestMethod.POST)
	public String upload(@RequestPart("uploadImage") MultipartFile image) throws IOException {
		if (!image.isEmpty()) {// 파일이 선택 되고 있음
			String folder = application.getInitParameter("contentFolder");
			File contents = new File(folder);
			String contentType = application.getMimeType(image.getOriginalFilename());
			if (contentType == null || !contentType.startsWith("image/")) {// 업로드할 수 없는 종류의 파일
				throw new BadRequestException("이미지만 업로드할 수 있음");
			}
//			image.saveTo(contents);
			image.transferTo(new File(contents, image.getOriginalFilename()));
		}
//		Post-Redirect-Get Pattern
		return "redirect:/02/imageForm.do";
	}

	@RequestMapping("/02/imageForm.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (application == null)
			application = req.getServletContext();
		String folder = application.getInitParameter("contentFolder");
		File contents = new File(folder);
		String[] children = contents.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("image/");
			}
		});

		Cookie[] cookies = req.getCookies();
		Cookie imageCookie = null;
		if (cookies != null) {
			for (Cookie tmp : cookies) {
				if (tmp.getName().equals("imageCookie")) {
					imageCookie = tmp;
					break;
				}
			}
		}
		if (imageCookie != null) {
			String decodedJson = URLDecoder.decode(imageCookie.getValue(), "UTF-8");
			req.setAttribute("imageCookie", decodedJson);
		}

		req.setAttribute("children", children);
		String view = "imageForm";
		return view;
	}
}
