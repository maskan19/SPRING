package kr.or.ddit.board.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.resolvers.RequestPart;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.utils.RegexUtils;
import kr.or.ddit.validator.BoardInsertGroup;
import kr.or.ddit.validator.CommonValidator;
import kr.or.ddit.validator.UpdateGroup;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

@Controller
public class BoardUpdateController {
	private IBoardService service = BoardServiceImpl.getInstance();
	private String[] filteringTokens = new String[] { "말미잘", "해삼" };

	@RequestMapping("/board/boardUpdate.do")
	public String updateForm(@RequestParam(value = "what", required = true) int bo_no, HttpServletRequest req) {
//		BoardVO search = new BoardVO();
//		search.setBo_no(bo_no);
//		BoardVO board = service.retrieveBoard(search);

		BoardVO board = service.retrieveBoard(BoardVO.builder().bo_no(bo_no).build());
		req.setAttribute("board", board);

		return "board/boardForm";
	}

	@RequestMapping(value = "/board/boardUpdate.do", method = RequestMethod.POST)
	public String update(@ModelAttribute("board") BoardVO board,
			@RequestPart(value = "bo_files", required = false) MultipartFile[] bo_files, HttpServletRequest req) {

//		String[] oldAttatch =  old_files.split(",");
//		List<AttatchVO> removeList = service.retrieveBoard(board).getAttatchList();//기존 리스트 불러옴
//		for (AttatchVO attatchVO : removeList) {
//			for (String attatch : oldAttatch) {//파라미터로 받아온 리스트(유지할 목록)
//				if(attatchVO.getAtt_savename().equals(attatch))//같다=유지해야한다
//					removeList.remove(attatchVO);
//			}
//		}
//		board.setRemoveList(removeList);

		if (bo_files != null) {
			List<AttatchVO> attatchList = new ArrayList<AttatchVO>();
			for (MultipartFile file : bo_files) {
				if (file.isEmpty())
					continue;
				attatchList.add(new AttatchVO(file));
			}
			if (attatchList.size() > 0)
				board.setAttatchList(attatchList);
		}

		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);

//		Class<?> groupHint = (Class<?>) req.getAttribute("groupHint");
//		if (groupHint == null)// 자유게시판의 경우
//			groupHint = BoardInsertGroup.class;

		boolean valid = new CommonValidator<BoardVO>().validate(board, errors, UpdateGroup.class);

		String view = null;
		String message = null;
		if (valid) {

			String replaceText = RegexUtils.filteringTokens(board.getBo_content(), 'ㅁ', filteringTokens);
			board.setBo_content(replaceText);

			// 검증 통과시 modify 로직 사용
			ServiceResult result = service.modifyBoard(board);
			if (ServiceResult.OK.equals(result)) {
				// 로직 실행 성공
				// 성공 결과를 확인할 수 있는 view 로 redirect
				view = "redirect:/board/boardView.do?what=" + board.getBo_no();
			} else {
				// 로직 실행 실패
				// 다시 명령이 발생할 수 있는 곳으로 이동
				message = "비밀번호 오류";
				view = "board/boardForm";
			}
		} else {
			view = "board/boardForm";
		}

		req.setAttribute("message", message);

		return view;
	}

}
