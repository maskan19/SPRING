package kr.or.ddit.board.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.utils.RegexUtils;
import kr.or.ddit.validator.BoardInsertGroup;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.NoticeInsertGroup;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardInsertController {
	private String[] filteringTokens = new String[] {"말미잘", "해삼"};
	
	@Inject
	private IBoardService service;
		
	@RequestMapping("/board/noticeInsert.do")
	public String noticeForm(@ModelAttribute("board") BoardVO board) {
		board.setBo_type("NOTICE");
		return "board/boardForm";
	}
	
	@RequestMapping(value="/board/noticeInsert.do", method=RequestMethod.POST)
	public String noticeInsert(
			@ModelAttribute("board") BoardVO board
			, HttpServletRequest req) {
		req.setAttribute("groupHint", NoticeInsertGroup.class);
		return insert(board, req);
	}
	
	@RequestMapping("/board/boardInsert.do")
	public String form(@ModelAttribute("board") BoardVO board
			, @RequestParam(value="parent", required=false, defaultValue="0") int parent
			) {
		board.setBo_type("BOARD");
		board.setBo_parent(parent);
		return "board/boardForm";
	}
	
	@RequestMapping(value="/board/boardInsert.do", method=RequestMethod.POST)
	public String insert(
			@ModelAttribute("board") BoardVO board
			, HttpServletRequest req
			) {
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		Class<?> groupHint = (Class<?>) req.getAttribute("groupHint");
		if(groupHint==null)
			groupHint = BoardInsertGroup.class;
		boolean valid = new CommonValidator<BoardVO>()
						.validate(board, errors, groupHint);
		
		String view = null;
		String message = null;
		if(valid) {
			
			String replaceText = 
					RegexUtils.filteringTokens(board.getBo_content(), 'ㅁ', filteringTokens);
			board.setBo_content(replaceText);
			
			ServiceResult result = service.createBoard(board);
			if(ServiceResult.OK.equals(result)) {
				view = "redirect:/board/boardView.do?what="+board.getBo_no();
			}else {
				message = "서버 오류";
				view = "board/boardForm";
			}
		}else {
			view = "board/boardForm";
		}
		
		req.setAttribute("message", message);
		
		return view;
	}
}









