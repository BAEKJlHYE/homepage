package anonymous.homepage.main.web;

import anonymous.homepage.buld.service.BuldService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    // 메인 화면 이동
    @GetMapping("/main.do")
    public String getMainPage(HttpServletRequest request, Model model) {
        Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);
        if (!MapUtils.isEmpty(redirectMap)) {
            model.addAttribute(redirectMap.get("loginVo"));
        }

        model.addAttribute("buldList", buldService.selectBuldList());
        return "main/main";
    }
}
