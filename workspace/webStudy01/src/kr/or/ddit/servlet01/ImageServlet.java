// d:\contents\coffee1.jpg : 파일시스템 이기 때문에 클라이언트가 다이렉트로 접근 불가

//1. 네트워크가 접근 할 수 있는 친구인지 먼저 확인
//2. 클라이언트가 접근 할 수 있도록 서블릿을 이용해 전달

package kr.or.ddit.servlet01;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;

/*
 * 400 번대는 클라이언트의 잘못
 * 500 번대는 서버측의 잘못
 */

@WebServlet("/01/image.do")
public class ImageServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imageFilename = req.getParameter("image");
		
		if(imageFilename == null || imageFilename.isEmpty()) {	// 파라미터 값이 null이냐 화이트스페이스(공백) 값이냐
			// 사용자에 의해 실패가 됐을 경우 상태 코드 값을 반환한다.
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);	// 클라이언트에게 상태코드를 전달한다. 400 페이지
			return; 
		}
		
		// 클라이언트가 요구한 자원이 서버에 존재하는지 유무를 판단.
		String folder = "d:/contents/images";
		File imageFile = new File(folder, imageFilename);
		if(!imageFile.exists()) {	// 서버에 해당 요청 데이터가 없을 경우
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);// 404 페이지
			return;			
		}
		
		// 해당 요청 데이터가 이미지 파일을 요청한 건지를 판단
		
		String mime = getServletContext().getMimeType(imageFilename);	// 해당 파일명의 확장자를 mime으로
		// 확장자가 web.xml에 존재 하지 않을 경우엔 mime == null 을 띄운다
		if(mime == null || !mime.startsWith("image/")) {	// 확장자가 이미지가 아닐 경우
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);	// 클라이언트가 요구하는 것을 서버측에서 해결할 수 없을 때 415번
			return;
		}
		resp.setContentType(mime);
		
		
		// 입력
		//InputStream : 1byte
		//Reader : 2byte
		FileInputStream fis = new FileInputStream(imageFile);
		
		// 출력
		OutputStream os = resp.getOutputStream();
		
		// 대용량 파일을 임시메모리에 넣어놓기
		byte[] buffer = new byte[1024];
		int pointer = -1;
		while((pointer = fis.read(buffer)) != -1) {
			os.write(buffer, 0, pointer);	// buffer를 읽고, 0번째부터 다시 읽고, 길이까지 읽어라
		}
	}
}