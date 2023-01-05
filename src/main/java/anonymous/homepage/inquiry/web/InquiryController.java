package anonymous.homepage.inquiry.web;

import anonymous.homepage.cd.service.CdService;
import anonymous.homepage.inquiry.service.InquiryService;
import anonymous.homepage.inquiry.vo.InquiryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {
    private final InquiryService inquiryService;
    private final CdService cdService;

    @GetMapping("/registerInquiry.do")
    public String registerInquiry(Model model) {
        model.addAttribute("headers", cdService.selectCdList("A06"));

        return "inquiry/inquiryRegister";
    }

    @PostMapping("/insertInquiry.do")
    @ResponseBody
    public String insertInquiry(@ModelAttribute InquiryVO inquiryVO, RedirectAttributes redirect) {
        inquiryService.insertInquiry(inquiryVO);

        return "ok";
    }
}
