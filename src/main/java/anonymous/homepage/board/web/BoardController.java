package anonymous.homepage.board.web;

import anonymous.homepage.board.service.BoardService;
import anonymous.homepage.board.vo.BoardVO;
import anonymous.homepage.cd.service.CdService;
import anonymous.homepage.file.FileStore;
import anonymous.homepage.file.service.FileService;
import anonymous.homepage.file.vo.AtchFileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CdService cdService;
    private final FileStore fileStore;
    private final FileService fileService;

    // 공지사항 목록 화면 이동
    @GetMapping("/selectBoardList.do")
    public String selectBoardList(@ModelAttribute BoardVO boardVO, Model model) {
        // 페이징 처리
        boardVO.setRecordUnit(boardVO.getRecordUnit() == 0 ? 10 : boardVO.getRecordUnit());
        boardVO.setPageUnit(boardVO.getPageUnit() == 0 ? 10 : boardVO.getPageUnit());
        boardVO.setNowPage(boardVO.getNowPage() == 0 ? 1 : boardVO.getNowPage());

        // 검색어 처리
        String originalSearchKeyword = boardVO.getSearchKeyword();
        if(StringUtils.hasText(originalSearchKeyword)) {
            boardVO.setSearchKeyword(originalSearchKeyword.replaceAll(" ", "|"));
        }

        // 게시판 조회
        List<BoardVO> boardList = boardService.selectBoardList(boardVO);
        boardVO.setRecordCount(boardService.selectBoardCount(boardVO));
        boardVO.setSearchKeyword(originalSearchKeyword);

        // 데이터 전달
        model.addAttribute("boardInfo", boardVO);
        model.addAttribute("boardList", boardList);
        model.addAttribute("searchSeCds", cdService.selectCdList("A05"));

        return "board/boardList";
    }

    // 공지사항 상세 화면 이동
    @GetMapping("/selectBoard.do")
    public String selectBoard(@ModelAttribute BoardVO boardVO, Model model) {
        // 게시글 조회
        BoardVO board = boardService.selectBoard(boardVO);
        
        // 조회수 증가
        if(!board.getWriter().equals(boardVO.getUserId())) {
            boardService.updateBoardViewCount(boardVO);
        }

        // 데이터 전달
        model.addAttribute("boardInfo", boardVO);
        model.addAttribute("board", board);

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
        String resultMessage;
        String redirectUrl;

        if(boardVO.getIsPermitted()) {
            boardService.insertBoard(boardVO);
            resultMessage = "게시글이 등록되었습니다.";
            redirectUrl = "/board/selectBoard.do";

        } else {
            resultMessage = "권한이 없습니다.";
            redirectUrl = "/board/selectBoardList.do";
        }

        boardVO.setResultMessage(resultMessage);
        redirect.addFlashAttribute("boardVO", boardVO);
        return "redirect:" + redirectUrl;
    }

    // 공지사항 수정
    @PostMapping("/updateBoard.do")
    public String updateBoard(@ModelAttribute BoardVO boardVO, RedirectAttributes redirect) {
        String resultMessage;
        String redirectUrl;

        if(boardVO.getIsPermitted()) {
            boardService.updateBoard(boardVO);
            resultMessage = "게시글이 수정되었습니다.";
            redirectUrl = "/board/selectBoard.do";

        } else {
            resultMessage = "권한이 없습니다.";
            redirectUrl = "/board/selectBoardList.do";
        }

        boardVO.setResultMessage(resultMessage);
        redirect.addFlashAttribute("boardVO", boardVO);
        return "redirect:" + redirectUrl;
    }

    // 공지사항 삭제
    @PostMapping("/deleteBoard.do")
    public String deleteBoard(@ModelAttribute BoardVO boardVO, RedirectAttributes redirect) {
        String resultMessage;
        if(boardVO.getIsPermitted()) {
            boardService.deleteBoard(boardVO);
            resultMessage = "게시글이 삭제되었습니다.";
            boardVO.setNowPage(1);

        } else {
            resultMessage = "권한이 없습니다.";
        }

        boardVO.setResultMessage(resultMessage);
        redirect.addFlashAttribute("boardVO", boardVO);
        return "redirect:/board/selectBoardList.do";
    }

    // 파일 업로드 테스트
    @GetMapping("/fileUploadTest.do")
    public String fileUpload() throws IOException {
        return "board/fileUpload";
    }

    // 파일 업로드 테스트
    @PostMapping("/fileUploadTest.do")
    @ResponseBody
    public String fileUploadTest(@ModelAttribute BoardVO boardVO) throws IOException {
        List<AtchFileVO> atchFiles = fileStore.saveFiles(boardVO.getUploadFiles());
        boardVO.setAtchFiles(atchFiles);
        fileService.saveFiles(boardVO);
        // ex. 게시글 첨부문서ID 세팅 후 게시글 insert

        return "okay";
    }

    // 파일 이미지 조회 테스트
    @GetMapping("/fileView.do")
    public String fileView(Model model) {
        model.addAttribute("atchFiles", fileService.selectAtchFileList("D0000004"));
        return "board/fileView";
    }

    // js test
    @GetMapping("/jsTest.do")
    public String goToJsTest() {
        return "board/jsTest";
    }
}
