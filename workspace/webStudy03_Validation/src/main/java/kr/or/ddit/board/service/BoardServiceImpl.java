package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.dao.AttatchDAOImpl;
import kr.or.ddit.board.dao.BoardDAOImpl;
import kr.or.ddit.board.dao.IAttatchDAO;
import kr.or.ddit.board.dao.IBoardDAO;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public class BoardServiceImpl implements IBoardService {

	private IBoardDAO boardDAO = BoardDAOImpl.getInstance();
	private IAttatchDAO attDAO = AttatchDAOImpl.getInstance();
	private SqlSessionFactory sessionFactory = CustomSqlSessionFactoryBuilder.getSessionFactory();
	private File saveFolder = new File("D:/attatches");

	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	private static BoardServiceImpl self;

	public BoardServiceImpl() {
	}

	public static BoardServiceImpl getInstance() {
		if (self == null)
			self = new BoardServiceImpl();
		return self;
	}

	private void encodePassword(BoardVO board) {
		String bo_pass = board.getBo_pass();
		if (StringUtils.isBlank(bo_pass))
			return;
		try {
			String encodedPass = CryptoUtil.sha512(bo_pass);
			board.setBo_pass(encodedPass);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private int getFiles(BoardVO board) {
		int cnt = 0;
		List<AttatchVO> attatches = board.getAttatchList();
		for (AttatchVO attatch : attatches) {

		}
		return cnt;
	}

	private int deleteAttatch(BoardVO boardVO, SqlSession session) {
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}

		int[] attatchList = boardVO.getRemoveList();
		int cnt = 0;

		if (attatchList != null && attatchList.length > 0) {
			List<String> saveNames = attDAO.selectSaveNamesForDelete(boardVO);
			attDAO.deleteAttatches(boardVO, session);
			// 이진 데이터 삭제
			for(String saveName : saveNames) {
				File saveFile = new File(saveFolder, saveName);
				saveFile.delete();
			}
		}
		return cnt;
	}

	
	private int processes(BoardVO board, SqlSession session) {
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	      int cnt =0;
	      List<AttatchVO> attatchList = board.getAttatchList();
	      if(attatchList!=null && attatchList.size()>0) {
	         cnt += attDAO.insertAttatches(board, session);
	         try {
	            for(AttatchVO attatch : attatchList) {
//	               if(1==1)
//	                  throw new RuntimeException("강제 발생 예외");
	               attatch.saveTo(saveFolder);
	            }
	         } catch (IOException e) {
	            throw new RuntimeException(e);
	         }
	      }
	      return cnt;
	   }
	
	@Override
	public ServiceResult createBoard(BoardVO board) {
		ServiceResult result = ServiceResult.FAIL;
		// ==========비밀번호 암호화==========
		encodePassword(board);
		// ===============================
		try (SqlSession session = sessionFactory.openSession(false);// auto commit을 막음
		) {
			int cnt = boardDAO.insertBoard(board, session);
			if (cnt > 0) {
				// ==========첨부파일 처리==========
				cnt += processes(board, session);
				// ==============================
				if (cnt > 0) {
					result = ServiceResult.OK;
					session.commit();
				}
			}
		} // try block end
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
		BoardVO board = boardDAO.selectBoard(search);
		if (board == null)
			throw new CustomException(search.getBo_no() + "에 해당하는 게시글이 없음");
		return board;
	}

	@Override
	public ServiceResult modifyBoard(BoardVO boardVO) {
		ServiceResult result = ServiceResult.FAIL;
		try(
				SqlSession session = sessionFactory.openSession(false);
				){
			encodePassword(boardVO);
			int cnt = boardDAO.updateBoard(boardVO, session);
			if (cnt > 0) {
				cnt += deleteAttatch(boardVO, session);
				cnt += processes(boardVO, session);
				if (cnt > 0)
					result = ServiceResult.OK;
				session.commit();
			}
			
		}
		return result;
	}

	@Override
	public ServiceResult removeBoard(BoardVO boardVO) {
		ServiceResult result = ServiceResult.FAIL;
		try(
				SqlSession session = sessionFactory.openSession(false);
				){
		if (boardDAO.deleteBoard(boardVO, session) > 0)
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public AttatchVO download(int att_no) {
		return attDAO.selectAttatch(att_no);
	}

	@Override
	public boolean boardAuthenticate(BoardVO search) {
		BoardVO saved = boardDAO.selectBoard(search);
		encodePassword(search);
		String savedPass = saved.getBo_pass();
		String inputPass = search.getBo_pass();
		return savedPass.equals(inputPass);
	}

}
