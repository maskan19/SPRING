package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import kr.or.ddit.vo.BoardVO;

public class IBoardDAOTest {

	private IBoardDAO dao = new BoardDAOImpl();
	
	@Test
	public void testSelectBoard() {
		BoardVO search = new BoardVO();
		search.setBo_no(1522);
		BoardVO board = dao.selectBoard(search);
		assertEquals(2, board.getAttatchList().size());
		
		search.setBo_no(499);
		board = dao.selectBoard(search);
		assertEquals(0, board.getAttatchList().size());
	}

}
