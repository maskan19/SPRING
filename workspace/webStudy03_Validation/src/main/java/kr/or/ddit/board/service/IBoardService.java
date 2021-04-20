package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.AttatchVO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PagingVO;

public interface IBoardService {
	public ServiceResult createBoard(BoardVO board);

	public int retrieveBoardCount(PagingVO<BoardVO> pagingVO);
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO);
	/**
	 * 
	 * @param search
	 * @return 존재하지 않는다면  custom exception
	 */
	public BoardVO retrieveBoard(BoardVO search);
	/**
	 * 인증 > 게시글 존재 확인 > 실행
	 * @param boardVO
	 * @return ServiceResult 
	 */
	public ServiceResult modifyBoard(BoardVO boardVO);
	/**
	 * 인증 > 게시글 존재 확인 > 실행
	 * @param boardVO
	 * @return ServiceResult
	 */
	public ServiceResult removeBoard(BoardVO boardVO);
	
	/**
	 * 
	 * @param att_no
	 * @return
	 */
	public AttatchVO download(int att_no);
	
	public boolean boardAuthenticate(BoardVO search);
}
