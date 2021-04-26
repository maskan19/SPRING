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
	public int insertAttatches(BoardVO board, SqlSession session);
	public AttatchVO selectAttatch(int att_no);
	public List<String> selectSaveNamesForDelete(BoardVO board);
	public int deleteAttathes(BoardVO board, SqlSession session);
}
