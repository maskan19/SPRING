package kr.or.ddit.board.dao;

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
	 * @return 입력 성공시 1 
	 */
	public int insertAttaches(BoardVO boardVO);

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
	 * @return
	 */
	public int deleteAttatches(BoardVO boardVO);
	
}
