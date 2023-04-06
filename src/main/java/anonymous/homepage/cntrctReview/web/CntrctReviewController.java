package anonymous.homepage.cntrctReview.web;

import anonymous.homepage.cd.service.CdService;
import anonymous.homepage.cntrctReview.service.CntrctReviewService;
import anonymous.homepage.cntrctReview.vo.CntrctReviewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cntrctReview")
public class CntrctReviewController {
    private final CntrctReviewService cntrctReviewService;
    private final CdService cdService;

    // 계약성공후기 목록 화면 이동
    @GetMapping("/selectBoardList.do")
    public String selectBoardList(@ModelAttribute CntrctReviewVO cntrctReviewVO, Model model) {
        // 페이징 처리
        cntrctReviewVO.setRecordUnit(cntrctReviewVO.getRecordUnit() == 0 ? 10 : cntrctReviewVO.getRecordUnit());
        cntrctReviewVO.setPageUnit(cntrctReviewVO.getPageUnit() == 0 ? 10 : cntrctReviewVO.getPageUnit());
        cntrctReviewVO.setNowPage(cntrctReviewVO.getNowPage() == 0 ? 1 : cntrctReviewVO.getNowPage());

        // 검색어 처리
        String originalSearchKeyword = cntrctReviewVO.getSearchKeyword();
        if(StringUtils.hasText(originalSearchKeyword)) {
            cntrctReviewVO.setSearchKeyword(originalSearchKeyword.replaceAll(" ", "|"));
        }

        // 게시판 조회
        List<CntrctReviewVO> boardList = cntrctReviewService.selectBoardList(cntrctReviewVO);
        cntrctReviewVO.setRecordCount(cntrctReviewService.selectBoardCount(cntrctReviewVO));
        cntrctReviewVO.setSearchKeyword(originalSearchKeyword);

        // 데이터 전달
        model.addAttribute("boardInfo", cntrctReviewVO);
        model.addAttribute("boardList", boardList);
        model.addAttribute("searchSeCds", cdService.selectCdList("A05"));

        return "cntrctReview/boardList";
    }

    // 계약성공후기 상세 화면 이동
    @GetMapping("/selectBoard.do")
    public String selectBoard(@ModelAttribute CntrctReviewVO cntrctReviewVO, Model model) {
        // 게시글 조회
        CntrctReviewVO board = cntrctReviewService.selectBoard(cntrctReviewVO);

        // 데이터 전달
        model.addAttribute("boardInfo", cntrctReviewVO);
        model.addAttribute("board", board);

        return "cntrctReview/board";
    }

    // 계약성공후기 등록 화면 이동
    @GetMapping("/registerBoard.do")
    public String registerBoard(@ModelAttribute CntrctReviewVO cntrctReviewVO, Model model) {
        // 데이터 전달
        model.addAttribute("boardInfo", cntrctReviewVO);

        return "cntrctReview/boardRegister";
    }

    // 계약성공후기 수정 화면 이동
    @GetMapping("/modifyBoard.do")
    public String modifyBoard(@ModelAttribute CntrctReviewVO cntrctReviewVO, Model model) {
        // 데이터 전달
        model.addAttribute("boardInfo", cntrctReviewVO);
        model.addAttribute("board", cntrctReviewService.selectBoard(cntrctReviewVO));

        return "cntrctReview/boardModify";
    }

    // 계약성공후기 등록
    @PostMapping("/insertBoard.do")
    public String insertBoard(@ModelAttribute CntrctReviewVO cntrctReviewVO, RedirectAttributes redirect) {
        String resultMessage;
        String redirectUrl;

        if(cntrctReviewVO.getIsPermitted()) {
            cntrctReviewService.insertBoard(cntrctReviewVO);
            resultMessage = "게시글이 등록되었습니다.";
            redirectUrl = "/cntrctReview/selectBoard.do";

        } else {
            resultMessage = "권한이 없습니다.";
            redirectUrl = "/cntrctReview/selectBoardList.do";
        }

        cntrctReviewVO.setResultMessage(resultMessage);
        redirect.addFlashAttribute("cntrctReviewVO", cntrctReviewVO);
        return "redirect:" + redirectUrl;
    }

    // 계약성공후기 수정
    @PostMapping("/updateBoard.do")
    public String updateBoard(@ModelAttribute CntrctReviewVO cntrctReviewVO, RedirectAttributes redirect) {
        String resultMessage;
        String redirectUrl;

        if(cntrctReviewVO.getIsPermitted()) {
            cntrctReviewService.updateBoard(cntrctReviewVO);
            resultMessage = "게시글이 수정되었습니다.";
            redirectUrl = "/cntrctReview/selectBoard.do";

        } else {
            resultMessage = "권한이 없습니다.";
            redirectUrl = "/cntrctReview/selectBoardList.do";
        }

        cntrctReviewVO.setResultMessage(resultMessage);
        redirect.addFlashAttribute("cntrctReviewVO", cntrctReviewVO);
        return "redirect:" + redirectUrl;
    }

    // 계약성공후기 삭제
    @PostMapping("/deleteBoard.do")
    public String deleteBoard(@ModelAttribute CntrctReviewVO cntrctReviewVO, RedirectAttributes redirect) {
        String resultMessage;
        if(cntrctReviewVO.getIsPermitted()) {
            cntrctReviewService.deleteBoard(cntrctReviewVO);
            resultMessage = "게시글이 삭제되었습니다.";
            cntrctReviewVO.setNowPage(1);

        } else {
            resultMessage = "권한이 없습니다.";
        }

        cntrctReviewVO.setResultMessage(resultMessage);
        redirect.addFlashAttribute("cntrctReviewVO", cntrctReviewVO);
        return "redirect:/cntrctReview/selectBoardList.do";
    }
}
