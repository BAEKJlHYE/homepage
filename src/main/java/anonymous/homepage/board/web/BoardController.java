package anonymous.homepage.board.web;

import anonymous.homepage.board.service.BoardService;
import anonymous.homepage.board.vo.BoardVO;
import anonymous.homepage.cd.service.CdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CdService cdService;

    // 공지사항 목록 화면 이동
    @GetMapping("/selectBoardList.do")
    public String selectBoardList(@ModelAttribute BoardVO boardVO, Model model) {
        // 페이징 처리
        boardVO.setRecordUnit(boardVO.getRecordUnit() == 0 ? 10 : boardVO.getRecordUnit());
        boardVO.setPageUnit(boardVO.getPageUnit() == 0 ? 10 : boardVO.getPageUnit());
        boardVO.setNowPage(boardVO.getNowPage() == 0 ? 1 : boardVO.getNowPage());
        boardVO.setRecordCount(boardService.selectBoardCount());

        // 데이터 전달
        model.addAttribute("boardInfo", boardVO);
        model.addAttribute("boardList", boardService.selectBoardList(boardVO));
        return "board/boardList";
    }

    // 공지사항 상세 화면 이동
    @GetMapping("/selectBoard.do")
    public String selectBoard(@ModelAttribute BoardVO boardVO, Model model) {
        // 조회수 증가
        boardService.updateBoardViewCount(boardVO);

        // 데이터 전달
        model.addAttribute("boardInfo", boardVO);
        model.addAttribute("board", boardService.selectBoard(boardVO));

        return "board/board";
    }

    // 공지사항 등록 화면 이동
    @GetMapping("/registerBoard.do")
    public String registerBoard(@ModelAttribute BoardVO boardVO, Model model) {
        // 데이터 전달
        model.addAttribute("boardInfo", boardVO);
        model.addAttribute("headers", cdService.selectCdList("A04"));

        return "board/boardRegister";
    }

    // 공지사항 수정 화면 이동
    @GetMapping("/modifyBoard.do")
    public String modifyBoard(@ModelAttribute BoardVO boardVO, Model model) {
        // 데이터 전달
        model.addAttribute("boardInfo", boardVO);
        model.addAttribute("board", boardService.selectBoard(boardVO));
        model.addAttribute("headers", cdService.selectCdList("A04"));

        return "board/boardModify";
    }

    // 공지사항 등록
    @PostMapping("/insertBoard.do")
    public String insertBoard(@ModelAttribute BoardVO boardVO, RedirectAttributes redirect) {
        boardService.insertBoard(boardVO);

        boardVO.setResultMessage("게시글이 등록되었습니다.");
        redirect.addFlashAttribute("boardVO", boardVO);

        return "redirect:/board/selectBoard.do";
    }

    // 공지사항 수정
    @PostMapping("/updateBoard.do")
    public String updateBoard(@ModelAttribute BoardVO boardVO, RedirectAttributes redirect) {
        boardService.updateBoard(boardVO);

        boardVO.setResultMessage("게시글이 수정되었습니다.");
        redirect.addFlashAttribute("boardVO", boardVO);

        return "redirect:/board/selectBoard.do";
    }

    // 공지사항 삭제
    @PostMapping("/deleteBoard.do")
    public String deleteBoard(@ModelAttribute BoardVO boardVO, RedirectAttributes redirect) {
        boardService.deleteBoard(boardVO);

        boardVO.setResultMessage("게시글이 삭제되었습니다.");
        boardVO.setNowPage(1);
        redirect.addFlashAttribute("boardVO", boardVO);

        return "redirect:/board/selectBoardList.do";
    }
}
