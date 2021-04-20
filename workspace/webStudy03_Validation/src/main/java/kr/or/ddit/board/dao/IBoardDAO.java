package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 게시글 관리를 위한 persistence layer
 *
 */
public interface IBoardDAO {

	/**
	 * 
	 * @param board 입력할 BoardVO
	 * @param session TODO
	 * @return 입력 성공시 1
	 */
	public int insertBoard(BoardVO board, SqlSession session);
	
	/**
	 * 
	 * @param pagingVO
	 * @return
	 */
	public int selectBoardCount(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 
	 * @param pagingVO 
	 * @return 인덱싱 된 BoardVO List
	 */
	public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO);
	
	/**
	 * 
	 * @param search 검색할 BoardVO객체
	 * @return BoardVO
	 */
	public BoardVO selectBoard(BoardVO search);
	
	/**
	 * 
	 * @param boardVO
	 * @param session TODO
	 * @return  수정 성공시 1
	 */
	public int updateBoard(BoardVO boardVO, SqlSession session);
	
	/**
	 * 
	 * @param boardVO 검색할 BoardVO 객체
	 * @return 삭제 성공시 1
	 */
	public int deleteBoard(BoardVO boardVO, SqlSession session);
}
