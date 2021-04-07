package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet{
	private IMemberService service =
			new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		SearchVO searchVO = new SearchVO(searchType, searchWord);
		
		
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(pageParam!=null && pageParam.matches("\\d+")) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<MemberVO> pagingVO = new PagingVO(7, 2);
		pagingVO.setCurrentPage(currentPage);
		// 검색 조건
		pagingVO.setSimpleSearch(searchVO);
		
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<MemberVO> memberList = 
				service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);
		
		req.setAttribute("pagingVO", pagingVO);
		
		String view = "/WEB-INF/views/member/memberList.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
}










