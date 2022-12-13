package anonymous.homepage.board.web;

import anonymous.homepage.board.service.BoardService;
import anonymous.homepage.board.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/selectBoardList.do")
    public String selectBoardList(Model model) {
        model.addAttribute("boardList", boardService.selectBoardList());
        return "board/boardList";
    }

    @GetMapping("/selectBoard.do")
    public String selectBoard(Model model) {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(1);
        model.addAttribute("board", boardService.selectBoard(boardVO));

        return "board/board";
    }

    @GetMapping("/registerBoard.do")
    public String registerBoard() {
        return "board/boardRegister";
    }

    @GetMapping("/selectBoardCount.do")
    @ResponseBody
    public String selectBoardCount() {
        int count = boardService.selectBoardCount();
        log.info("count: {}", count);

        return "ok";
    }

    @GetMapping("/insertBoard.do")
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
    public String deleteBoard() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(3);
        boardService.deleteBoard(boardVO);

        return "ok";
    }

    @GetMapping("/updateBoardViewCount.do")
    @ResponseBody
    public String updateBoardViewCount() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(3);
        boardVO.setViewCount(2);
        boardService.updateBoardViewCount(boardVO);

        return "ok";
    }

    @GetMapping("/updateBoardSuggestionCount.do")
    @ResponseBody
    public String updateBoardSuggestionCount() {
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(3);
        boardVO.setSuggestionCount(3);
        boardService.updateBoardSuggestionCount(boardVO);

        return "ok";
    }
}
