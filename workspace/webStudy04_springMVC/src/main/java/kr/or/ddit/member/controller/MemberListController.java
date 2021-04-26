package kr.or.ddit.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
public class MemberListController{
	private IMemberService service =
			new MemberServiceImpl();
	
	@RequestMapping("/member/memberView.do")
	public String view(
		@RequestParam("who") String who
		, HttpServletRequest req
	) {
		MemberVO member = service.retrieveMember(who);
		req.setAttribute("member", member);
		return "member/mypage";
	}
	
	@RequestMapping("/member/memberList.do")
	public String memberList(
			@ModelAttribute("searchVO") SearchVO searchVO
			, @RequestParam(value="page", required=false, defaultValue="1") int currentPage
			, HttpServletRequest req){
		
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
		
		return "member/memberList";
	}
}










