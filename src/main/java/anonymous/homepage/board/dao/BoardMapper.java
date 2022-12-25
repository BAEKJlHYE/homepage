package anonymous.homepage.board.dao;

import anonymous.homepage.board.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<BoardVO> selectBoardList(BoardVO boardVO);
    public BoardVO selectBoard(BoardVO boardVO);
    public int selectBoardCount();
    public int insertBoard(BoardVO boardVO);
    public int updateBoard(BoardVO boardVO);
    public int deleteBoard(BoardVO boardVO);
    public int updateBoardViewCount(BoardVO boardVO);
    public int updateBoardSuggestionCount(BoardVO boardVO);
}
