package kr.or.ddit.board.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.DeleteGroup;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardDeleteController {
	private IBoardService service = new BoardServiceImpl();

	@RequestMapping(value = "/board/boardDelete.do", method = RequestMethod.POST)
	public String delete(@ModelAttribute("board") BoardVO board, HttpServletRequest req, HttpSession session) {
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);

		// delete group hint 를 적용한 검증
		boolean valid = new CommonValidator<BoardVO>().validate(board, errors, DeleteGroup.class);
		String view = null;
		if (valid) {
			ServiceResult result = service.removeBoard(board);
			if (ServiceResult.OK.equals(result)) {
				view = "redirect:/board/boardList.do";
			} else {
				session.setAttribute("message", "비밀번호 오류");
				view = "redirect:/board/boardView.do?what=" + board.getBo_no();
			}
		} else {
			session.setAttribute("message", "필수 데이터 누락");
			view = "redirect:/board/boardView.do?what=" + board.getBo_no();
		}
		return view;
	}
}
