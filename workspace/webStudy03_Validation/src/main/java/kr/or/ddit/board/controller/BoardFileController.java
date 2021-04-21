package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.vo.AttatchVO;

@Controller
public class BoardFileController {
//	private IAttatchService service = AttatchServiceImpl.getInstance();
	private IBoardService service = BoardServiceImpl.getInstance();
	
	@RequestMapping("/board/download.do")
	public String download(@RequestParam(value="what", required=true) int att_no, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		AttatchVO attatch = service.download(att_no);
		File saveFolder = new File("d:/attatches");
		File downloadFile = new File(saveFolder, attatch.getAtt_savename());
//		헤더는 바디 완성 전에
//		;는 여러 값을 보낼 수 있다.
//		setHeader는 문자열로만 보낼 수 있음. setIntHeader는 integer까지만 보낼 수 있음. 
//		공백이 들어가면 토큰 역할을 해서 파일 명이 잘린다.
		String agent = req.getHeader("User-Agent");
		String filename = attatch.getAtt_filename();
		if(StringUtils.containsIgnoreCase(agent, "trident")) {
			// 파일 네임을 인코딩 작업
			filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", " ");
		}else {
			byte[] bytes = filename.getBytes();
			filename = new String(bytes, "ISO-8859-1");
		}
		resp.setHeader("Content-Disposition", "attatchment;filename=\""+filename+"\"");
		resp.setHeader("Content-Length", attatch.getAtt_size()+"");
		resp.setContentType("application/octet-stream");
		try(
			OutputStream os = resp.getOutputStream();	
		){
			FileUtils.copyFile(downloadFile, os);
		}
		return null;
	}
	
	
	@RequestMapping(value = "/board/boardImage.do", method = RequestMethod.POST)
	public String imageUpload(@RequestPart("upload") MultipartFile upload, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		String saveFolderURL = "/boardImages";
		String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
		File saveFolder = new File(saveFolderPath);
		if(!saveFolder.exists()) saveFolder.mkdirs();
		Map<String, Object> resultMap = new HashMap<>();
		if (!upload.isEmpty()) {
			
			upload.saveTo(saveFolder);
			
			int uploaded = 1;
			String fileName = upload.getOriginalFilename();
			String saveName = upload.getUniqueSaveName();
			String url = req.getContextPath() + saveFolderURL + "/" + saveName;
			resultMap.put("uploaded", uploaded);
			resultMap.put("fileName", fileName);
			resultMap.put("url", url);
		}

		resp.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = resp.getWriter();) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, resultMap);
		}

		return null;
	}
}
