package anonymous.homepage.main.web;

import anonymous.homepage.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    // 메인 화면 이동
    @GetMapping("/main.do")
    public String getMainPage(HttpServletRequest request, Model model) {
        Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);
        if (!MapUtils.isEmpty(redirectMap)) {
            model.addAttribute(redirectMap.get("loginVo"));
        }

        return "main/main";
    }

}
