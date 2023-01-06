package anonymous.homepage.inquiry.web;

import anonymous.homepage.board.vo.BoardVO;
import anonymous.homepage.cd.service.CdService;
import anonymous.homepage.inquiry.service.InquiryService;
import anonymous.homepage.inquiry.vo.InquiryVO;
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
@RequestMapping("/inquiry")
public class InquiryController {
    private final InquiryService inquiryService;
    private final CdService cdService;

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
        inquiryVO.setRecordCount(inquiryService.selectInquiryCount(inquiryVO));
        inquiryVO.setSearchKeyword(originalSearchKeyword);

        // 데이터 전달
        model.addAttribute("inquiryInfo", inquiryVO);
        model.addAttribute("inquiryList", inquiryList);
        model.addAttribute("searchSeCds", cdService.selectCdList("A05"));

        return "inquiry/inquiryList";
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
    @ResponseBody
    public String insertInquiry(@ModelAttribute InquiryVO inquiryVO, RedirectAttributes redirect) {
        inquiryService.insertInquiry(inquiryVO);

        return "ok";
    }
}
