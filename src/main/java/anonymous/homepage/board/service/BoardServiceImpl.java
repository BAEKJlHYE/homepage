package anonymous.homepage.board.service;

import anonymous.homepage.board.dao.BoardMapper;
import anonymous.homepage.board.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;

    public List<BoardVO> selectBoardList() {
        return boardMapper.selectBoardList();
    }

    public BoardVO selectBoard(BoardVO boardVO) {
        return boardMapper.selectBoard(boardVO);
    }

    public int selectBoardCount() {
        return boardMapper.selectBoardCount();
    }

    public int insertBoard(BoardVO boardVO) {
        return boardMapper.insertBoard(boardVO);
    }

    public int updateBoard(BoardVO boardVO) {
        return boardMapper.updateBoard(boardVO);
    }

    public int deleteBoard(BoardVO boardVO) {
        return boardMapper.deleteBoard(boardVO);
    }

    public int updateBoardViewCount(BoardVO boardVO) {
        return boardMapper.updateBoardViewCount(boardVO);
    }

    public int updateBoardSuggestionCount(BoardVO boardVO) {
        return boardMapper.updateBoardSuggestionCount(boardVO);
    }
}
