package anonymous.homepage.board.web;

import anonymous.homepage.board.service.BoardService;
import anonymous.homepage.board.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/selectBoardList.do")
    public List<BoardVO> selectBoardList() {
        return boardService.selectBoardList();
    }

    @GetMapping("/selectBoard.do")
    public BoardVO selectBoard() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(1);
        BoardVO result = boardService.selectBoard(boardVO);

        return result;
    }

    @GetMapping("/selectBoardCount.do")
    public String selectBoardCount() {
        int count = boardService.selectBoardCount();
        log.info("count: {}", count);

        return "ok";
    }

    @GetMapping("/insertBoard.do")
    public String insertBoard() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("title2");
        boardVO.setContent("content2");
        boardVO.setUserId("admin");
        boardVO.setBoardId(0);
        boardService.insertBoard(boardVO);

        return "ok";
    }

    @GetMapping("/updateBoard.do")
    public String updateBoard() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(3);
        boardVO.setTitle("title3");
        boardVO.setContent("content3");
        boardVO.setBoardOrder(1);
        boardVO.setUserId("admin");
        boardService.updateBoard(boardVO);

        return "ok";
    }

    @GetMapping("/deleteBoard.do")
    public String deleteBoard() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(3);
        boardService.deleteBoard(boardVO);

        return "ok";
    }

    @GetMapping("/updateBoardViewCount.do")
    public String updateBoardViewCount() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(3);
        boardVO.setViewCount(2);
        boardService.updateBoardViewCount(boardVO);

        return "ok";
    }

    @GetMapping("/updateBoardSuggestionCount.do")
    public String updateBoardSuggestionCount() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(3);
        boardVO.setSuggestionCount(3);
        boardService.updateBoardSuggestionCount(boardVO);

        return "ok";
    }
}
