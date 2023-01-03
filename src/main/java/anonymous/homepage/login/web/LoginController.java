package anonymous.homepage.login.web;

import anonymous.homepage.login.service.LoginService;
import anonymous.homepage.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    // 로그인 화면 이동
    @GetMapping("/getLoginPage.do")
    public String getLoginPage(@ModelAttribute LoginVO loginVO, Model model) {
        return "login/login";
    }

    // 로그인
    @PostMapping("/selectUser.do")
    public String selectUser(@ModelAttribute("loginForm") LoginVO loginVO, Model model) {
        String returnUrl = "";
        LoginVO vo = loginService.selectUserByUsername(loginVO);

        if (ObjectUtils.isEmpty(vo)) {
            vo = new LoginVO();
            vo.setResultMessage("아이디 혹은 비밀번호가 일치하지 않습니다.");

            returnUrl = "redirect:/login/getLoginPage.do";
        } else {
            returnUrl = "redirect:/main/main.do";
        }

        model.addAttribute("loginVo", vo);
        return returnUrl;
    }

}
