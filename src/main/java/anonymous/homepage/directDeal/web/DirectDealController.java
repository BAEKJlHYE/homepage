package anonymous.homepage.directDeal.web;

import anonymous.homepage.directDeal.service.DirectDealService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/directDeal")
public class DirectDealController {
    private final DirectDealService directDealService;
}
