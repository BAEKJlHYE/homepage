package anonymous.homepage.inquiry.web;

import anonymous.homepage.inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {
    private final InquiryService inquiryService;
}
