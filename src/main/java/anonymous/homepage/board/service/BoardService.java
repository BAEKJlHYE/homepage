package anonymous.homepage.board.service;

import anonymous.homepage.board.vo.BoardVO;

import java.util.List;

public interface BoardService {
    public List<BoardVO> selectBoardList(BoardVO boardVO);
    public BoardVO selectBoard(BoardVO boardVO);
    public int selectBoardCount(BoardVO boardVO);
    public int insertBoard(BoardVO boardVO);
    public int updateBoard(BoardVO boardVO);
    public int deleteBoard(BoardVO boardVO);
    public int updateBoardViewCount(BoardVO boardVO);
    public int updateBoardSuggestionCount(BoardVO boardVO);
}
