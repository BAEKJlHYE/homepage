package anonymous.homepage.login.web;

import anonymous.homepage.login.service.LoginService;
import anonymous.homepage.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.thymeleaf.util.MapUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    // 로그인 화면 이동
    @GetMapping("/getLoginPage.do")
    public String getLoginPage(HttpServletRequest request, Model model) {
        Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);
        if (!MapUtils.isEmpty(redirectMap)) {
            model.addAttribute("loginVo", redirectMap.get("loginVo"));
        } else {
            model.addAttribute("loginVo", new LoginVO());
        }

        return "login/login";
    }

    // 로그인
    @PostMapping("/selectUser.do")
    public String selectUser(@ModelAttribute("loginForm") LoginVO loginVO, RedirectAttributes redirect) {
        String returnUrl = "";
        LoginVO vo = loginService.selectUserByUsername(loginVO);

        if (ObjectUtils.isEmpty(vo)) {
            vo = new LoginVO();
            vo.setResultMessage("아이디 혹은 비밀번호가 일치하지 않습니다.");

            returnUrl = "redirect:/login/getLoginPage.do";
        } else {
            returnUrl = "redirect:/main/main.do";
        }

        redirect.addFlashAttribute("loginVo", vo);
        return returnUrl;
    }

}
