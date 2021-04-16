package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.mvc.annotation.Controller;
import kr.or.ddit.mvc.annotation.RequestMapping;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

@Controller
public class BoardReadController {
	private IBoardService boardService = BoardServiceImpl.getInstance();
	private static final Logger logger = LoggerFactory.getLogger(BoardReadController.class);

//	private IAttatchDAO attDao = AttatchDAOImpl.getInstance();
	@RequestMapping("/board/boardView.do")
	public String viewForAjax(@RequestParam(value="what") int what,
			HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain;charset=UTF-8");

		String accept = req.getHeader("Content-Type");
		BoardVO search = new BoardVO();
		search.setBo_no(what);
		BoardVO boardVO = boardService.retrieveBoard(search);
		
		if (StringUtils.containsIgnoreCase(accept, "application")) {
			logger.info("board number :" + what);
			
			resp.setContentType("application/json;charset=UTF-8");
			// 마샬링+직렬화
			try (PrintWriter out = resp.getWriter();) {
				String cont  = boardVO.getBo_content();
				logger.info("board content :" + cont);
				out.println(boardVO.getBo_content());
			}
			return null;
		} else {
			req.setAttribute("board", boardVO);
			return "board/boardView";
		}
	}

	@RequestMapping("/board/boardList.do")
	public String boardList(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "searchWord", required = false) String searchWord,
			@RequestParam(value = "minDate", required = false) String minDate,
			@RequestParam(value = "maxDate", required = false) String maxDate, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
//		addAttribute(req);

		Map<String, Object> searchMap = new LinkedHashMap<String, Object>();
		searchMap.put("page", currentPage);
		searchMap.put("searchWord", searchWord);
		searchMap.put("minDate", minDate);
		searchMap.put("maxDate", maxDate);

		PagingVO<BoardVO> pagingVO = new PagingVO<>(10, 5);
		pagingVO.setSearchMap(searchMap);
		pagingVO.setCurrentPage(currentPage);

		int totalRecord = boardService.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<BoardVO> boardList = boardService.retrieveBoardList(pagingVO);
		pagingVO.setDataList(boardList);

		String accept = req.getHeader("Accept");
		String view = null;
		if (StringUtils.containsIgnoreCase(accept, "json")) {
			resp.setContentType("application/json;charset=UTF-8");
			// 마샬링+직렬화
			ObjectMapper mapper = new ObjectMapper();
			try (PrintWriter out = resp.getWriter();) {
				mapper.writeValue(out, pagingVO);// write 계열이 마샬링
			}
		} else {
			req.setAttribute("pagingVO", pagingVO);
			view = "board/boardList";
		}
		return view;
	}
}
