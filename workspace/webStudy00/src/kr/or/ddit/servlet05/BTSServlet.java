package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/bts/getContent.do", loadOnStartup=1)
public class BTSServlet extends HttpServlet {
	Map<String, String> btsMap;
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		btsMap = new LinkedHashMap<>();
		btsMap.put("bui", "뷔");
		btsMap.put("jhop", "제이홉");
		btsMap.put("jimin", "지민");
		btsMap.put("jin", "진");
		btsMap.put("jungkuk", "정국");
		btsMap.put("rm", "랩몬");
		btsMap.put("suga", "슈가");
		config.getServletContext().setAttribute("btsMap", btsMap);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//forward로 form.jsp
		req.getRequestDispatcher("/WEB-INF/views/btsForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//고른 후
		String bts = req.getParameter("bts");
		int status = validate(bts);
		if(status!=200) {
			resp.sendError(status);
			return;
		}
		
		String view = "/WEB-INF/views/bts/"+bts+".jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}

	private int validate(String bts) {
		int status = 200;
		if(bts==null || bts.isEmpty()) {
			status = 400;
		}else {
			if(!btsMap.containsKey(bts)) {
				status = 400;
			}
		}
		return status;
	}

}
