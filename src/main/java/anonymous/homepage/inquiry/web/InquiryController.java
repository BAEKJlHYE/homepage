package anonymous.homepage.inquiry.web;

import anonymous.homepage.cd.service.CdService;
import anonymous.homepage.inquiry.service.InquiryService;
import anonymous.homepage.inquiry.vo.InquiryVO;
import anonymous.homepage.util.Masking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {
    private final InquiryService inquiryService;
    private final CdService cdService;
    private final Masking masking;

    // 문의사항 목록 화면 이동
    @GetMapping("/selectInquiryList.do")
    public String selectInquiryList(@ModelAttribute InquiryVO inquiryVO, Model model) {
        // 페이징 처리
        inquiryVO.setRecordUnit(inquiryVO.getRecordUnit() == 0 ? 10 : inquiryVO.getRecordUnit());
        inquiryVO.setPageUnit(inquiryVO.getPageUnit() == 0 ? 10 : inquiryVO.getPageUnit());
        inquiryVO.setNowPage(inquiryVO.getNowPage() == 0 ? 1 : inquiryVO.getNowPage());

        // 검색어 처리
        String originalSearchKeyword = inquiryVO.getSearchKeyword();
        if(StringUtils.hasText(originalSearchKeyword)) {
            inquiryVO.setSearchKeyword(originalSearchKeyword.replaceAll(" ", "|"));
        }

        // 문의사항 조회
        List<InquiryVO> inquiryList = inquiryService.selectInquiryList(inquiryVO);
        if(!inquiryVO.getIsPermitted()) {
            for(InquiryVO inquiry : inquiryList)
                inquiry.setUpdateNm(masking.nameMasking(inquiry.getUpdateNm()));
        }

        inquiryVO.setRecordCount(inquiryService.selectInquiryCount(inquiryVO));
        inquiryVO.setSearchKeyword(originalSearchKeyword);

        // 데이터 전달
        model.addAttribute("inquiryInfo", inquiryVO);
        model.addAttribute("inquiryList", inquiryList);
        model.addAttribute("searchSeCds", cdService.selectCdList("A05"));

        return "inquiry/inquiryList";
    }

    // 비밀번호 확인
    @PostMapping("/checkPassword.do")
    @ResponseBody
    public Map<String, Object> checkPassword(@RequestBody InquiryVO inquiryVO) {
        int count = inquiryService.checkPassword(inquiryVO);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", count);

        return resultMap;
    }

    // 답변완료 처리
    @PostMapping("/changeAnswerYn.do")
    @ResponseBody
    public Map<String, Object> changeAnswerYn(@RequestBody InquiryVO inquiryVO) {
        int count = inquiryService.changeAnswerYn(inquiryVO);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("count", count);

        return resultMap;
    }

    // 문의사항 상세 화면 이동
    @GetMapping("/selectInquiry.do")
    public String selectInquiry(@ModelAttribute InquiryVO inquiryVO, Model model) {
        InquiryVO inquiry = inquiryService.selectInquiry(inquiryVO);
        if(!inquiryVO.getIsPermitted()) {
            inquiry.setUpdateNm(masking.nameMasking(inquiry.getUpdateNm()));
            inquiry.setTelNo(masking.telNoMasking(inquiry.getTelNo()));
        }

        model.addAttribute("inquiryInfo", inquiryVO);
        model.addAttribute("inquiry", inquiry);
        return "inquiry/inquiry";
    }

    // 문의사항 등록 화면 이동
    @GetMapping("/registerInquiry.do")
    public String registerInquiry(@ModelAttribute InquiryVO inquiryVO, Model model) {
        model.addAttribute("inquiryInfo", inquiryVO);
        model.addAttribute("headers", cdService.selectCdList("A06"));

        return "inquiry/inquiryRegister";
    }

    // 문의사항 등록
    @PostMapping("/insertInquiry.do")
    public String insertInquiry(@ModelAttribute InquiryVO inquiryVO, RedirectAttributes redirect) {
        inquiryService.insertInquiry(inquiryVO);
        inquiryVO.setResultMessage("게시글이 등록되었습니다.");
        redirect.addFlashAttribute("inquiryVO", inquiryVO);

        return "redirect:/inquiry/selectInquiry.do";
    }

    // 문의사항 삭제
    @PostMapping("/deleteInquiry.do")
    public String deleteInquiry(@ModelAttribute InquiryVO inquiryVO, RedirectAttributes redirect) {
        if (inquiryVO.getIsPermitted()) {
            inquiryService.deleteInquiry(inquiryVO);
            inquiryVO.setResultMessage("게시글이 삭제되었습니다.");
        } else {
            inquiryVO.setResultMessage("게시글 삭제에 실패하였습니다.");
        }
        redirect.addFlashAttribute("inquiryVO", inquiryVO);

        return "redirect:/inquiry/selectInquiryList.do";
    }
}
