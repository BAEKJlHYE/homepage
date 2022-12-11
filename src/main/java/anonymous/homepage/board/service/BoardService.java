package anonymous.homepage.board.service;

import anonymous.homepage.board.vo.BoardVO;

import java.util.List;

public interface BoardService {
    public List<BoardVO> selectBoardList();
    public BoardVO selectBoard(BoardVO boardVO);
    public int selectBoardCount();
    public int insertBoard(BoardVO boardVO);
    public int updateBoard(BoardVO boardVO);
    public int deleteBoard(BoardVO boardVO);
    public int updateBoardViewCount(BoardVO boardVO);
    public int updateBoardSuggestionCount(BoardVO boardVO);
}
