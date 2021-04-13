package kr.or.ddit.fileupload.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;

//@Controller
public class FileUploadController_bak {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController_bak.class);

	@RequestMapping("/fileUpload.do")
	public String form() {
		return "fileupload/uploadForm";
	}

	@RequestMapping(value = "/fileUpload.do", method = RequestMethod.POST)
	public String upload(@RequestParam("uploader") String uploader, HttpServletRequest req, HttpSession session)
			throws IOException, ServletException {
		ServletContext application = req.getServletContext();
		String saveFolderUrl = "/prodImages";
		File saveFolder = new File(application.getRealPath(saveFolderUrl));
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}

		Collection<Part> parts = req.getParts(); //Servlet 3.0부터 사용 가능

		int index = 1;
		for (Part tmp : parts) {
			if(tmp.getContentType()==null) continue; //String은 파일 처리를 하지 않음
			
			String saveFileName = processPart(tmp, saveFolder);
			String saveFileUrl = saveFolderUrl + "/" + saveFileName;
			session.setAttribute("uploadFile1" + index++, saveFileUrl);
			logger.info("saveFile : {}", saveFileUrl);
		}

		logger.info("uploader : {}", uploader);
		return "redirect:/fileUpload.do";
	}

	private String getOriginalFilename(Part part) {
//		Content-Disposition: form-data; name="uploadFile1"; filename=""
		String disposition = part.getHeader("Content-Disposition");
		int idx = disposition.indexOf("\"", disposition.indexOf("filename="));// filename= 이후부터 \를 찾아라
		String originalFilename = null;
		if (idx != -1) {
			originalFilename = disposition.substring(idx).replace("\"", "");
		}
		return originalFilename;
	}

	private String processPart(Part tmp, File saveFolder) throws IOException {
//		String originalFilename1 = tmp.getSubmittedFileName();// 서블릿 버전 3.1 이상
		String originalFilename1 = getOriginalFilename(tmp);
//		if(uploadFile1.getSize()!=0)

		if (StringUtils.isNotBlank(originalFilename1)) {//원본 파일 명이 없으면
			String mime1 = tmp.getContentType();
			if (!mime1.startsWith("image/")) {
				throw new BadRequestException();// 호출자(HandlerAdapter)에게 전송
			}
			File saveFile1 = new File(saveFolder, originalFilename1);
			byte[] buffer = new byte[1024];
			int cnt = -1;
			try (InputStream is1 = tmp.getInputStream(); FileOutputStream fos1 = new FileOutputStream(saveFile1);) {
				while ((cnt = is1.read(buffer)) != -1) {
					fos1.write(buffer, 0, cnt);
				}
			}
		}
		return originalFilename1;
	}
}
