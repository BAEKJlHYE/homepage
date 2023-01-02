package anonymous.homepage.login.web;

import anonymous.homepage.login.service.LoginService;
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
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    // 로그인 화면 이동
    @GetMapping("/selectLogin.do")
    public String selectLogin(@ModelAttribute LoginVO loginVO, Model model) {


        return "login/login";
    }

}
