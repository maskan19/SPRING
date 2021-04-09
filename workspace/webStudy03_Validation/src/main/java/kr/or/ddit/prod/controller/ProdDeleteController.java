package kr.or.ddit.prod.controller;

import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;


@Controller
public class ProdDeleteController {
	private IProdService service = ProdServiceImpl.getInstance();

	@RequestMapping(value = "/prod/prodDelete.do", method = RequestMethod.POST)
	public String prodDelete(@RequestParam("prod_id") String prod_id, HttpSession session) {
//		1. 요청 접수
		ServiceResult result = service.removeProd(prod_id);
		String view = null;
//		현재 리퀘스트 정보를 보존하지 않음
		switch (result) {
		case OK:
			session.invalidate();
			view = "redirect:/prod/prodList.do";
			break;
		default:
			view = "redirect:/mypage.do";
			session.setAttribute("message", "서버 오류");
			break;
		}
		return view;
	}
}
