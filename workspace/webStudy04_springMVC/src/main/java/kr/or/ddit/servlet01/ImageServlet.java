package kr.or.ddit.servlet01;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;

@Controller
public class ImageServlet{

	@RequestMapping("/01/image.do")
	public String doGet(
			@RequestParam("image") String imageFilename
			, HttpServletRequest req,
              HttpServletResponse resp)
              throws ServletException,
                     IOException{
		String folder = req.getServletContext().getInitParameter("contentFolder");			 
		File imageFile = new File(folder, imageFilename);
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		String mime = req.getServletContext().getMimeType(imageFilename);
		if(mime==null || !mime.startsWith("image/")) {
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return null;
		}
		
		resp.setContentType(mime);		
		try(
			FileInputStream fis = new FileInputStream(imageFile);
			OutputStream os = resp.getOutputStream();
		){		
			byte[] buffer = new byte[1024];
			int pointer = -1;
			while((pointer = fis.read(buffer))!=-1){
				os.write(buffer, 0, pointer);
			}
		}
		return null;
	}

}



















