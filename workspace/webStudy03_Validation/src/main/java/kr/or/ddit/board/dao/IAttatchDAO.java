package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;

/**
 * 첨부파일 관리를 위한 persistence layer
 *
 */
public interface IAttatchDAO {

	/**
	 * 
	 * @param boardVO 에 들어있는
	 * @param session TODO
	 * @return 입력 성공시 1 
	 */
	public int insertAttatches(BoardVO boardVO, SqlSession session);

	/**
	 * 
	 * 다운로드 받을 경우 사용할 것
	 * @param att_no
	 * @return
	 */
	public AttatchVO selectAttatch(int att_no);
	
	/**
	 * 
	 * @param boardVO
	 * @param session TODO
	 * @return
	 */
	public int deleteAttatches(BoardVO boardVO, SqlSession session);
	
	/**
	 * 삭제용 첨부파일 조회
	 * @param boardVO
	 * @return
	 */
	public List<String> selectSaveNamesForDelete(BoardVO boardVO);
	
}
