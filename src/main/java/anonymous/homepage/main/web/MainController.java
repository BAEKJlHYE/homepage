package anonymous.homepage.main.web;

import anonymous.homepage.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
    // 메인 화면 이동
    @GetMapping("/main.do")
    public String getMainPage(@ModelAttribute LoginVO loginVO, Model model) {
        return "main/main";
    }

}
