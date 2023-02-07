package anonymous.homepage.main.web;

import anonymous.homepage.buld.service.BuldService;
import anonymous.homepage.buld.vo.BuldVO;
import anonymous.homepage.main.service.MainService;
import anonymous.homepage.main.vo.MainVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.thymeleaf.util.MapUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
    private final BuldService buldService;

    private final MainService mainService;

    // 메인 화면 이동
    @GetMapping("/main.do")
    public String getMainPage(HttpServletRequest request, Model model) {
        Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);
        if (!MapUtils.isEmpty(redirectMap)) {
            model.addAttribute(redirectMap.get("loginVo"));
        }

        model.addAttribute("buldList", buldService.selectBuldList());
        model.addAttribute("buldDetail", new BuldVO());
        return "main/main";
    }

    // 메인 매물 상세 조회
//    @PostMapping("/selectMainBuldDetail.do")
    @RequestMapping(value = "/selectMainBuldDetail.do", method = { RequestMethod.POST })
    public String selectMainBuldDetail(@ModelAttribute("buldDetailForm") MainVO mainVO, Model model) {
        model.addAttribute("buldDetail", mainService.selectMainBuldDetail(mainVO));
        return "main/main :: #exPop";
    }
}
