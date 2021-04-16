package kr.or.ddit.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IAttatchDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDAO boardDAO = BoardDAOImpl.getInstance();
	private IAttatchDAO attDAO = AttatchDAOImpl.getInstance();
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	private static BoardServiceImpl self;
	
	private BoardServiceImpl(){
	}
	
	public static BoardServiceImpl getInstance() {
		if(self==null) self = new BoardServiceImpl();
		return self;
	}

	@Override
	public ServiceResult createBoard(BoardVO board) {
		ServiceResult result = ServiceResult.FAIL;
		if(boardDAO.insertBoard(board)>0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public int retrieveBoardCount(PagingVO<BoardVO> pagingVO) {
		return boardDAO.selectBoardCount(pagingVO);
	}

	@Override
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO) {
		return boardDAO.selectBoardList(pagingVO);
	}

	@Override
	public BoardVO retrieveBoard(BoardVO search) {
		return boardDAO.selectBoard(search);
	}

	@Override
	public ServiceResult modifyBoard(BoardVO boardVO) {
		ServiceResult result = ServiceResult.FAIL;
		if(boardDAO.updateBoard(boardVO)>0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public ServiceResult removeBoard(BoardVO boardVO) {
		ServiceResult result = ServiceResult.FAIL;
		if(boardDAO.deleteBoard(boardVO)>0) result = ServiceResult.OK;
		return result;
	}

	@Override
	public AttatchVO download(int att_no) {
		return attDAO.selectAttatch(att_no);
	}

}
