package anonymous.homepage.login.web;

import anonymous.homepage.login.config.CustomUserDetails;
import anonymous.homepage.login.service.LoginService;
import anonymous.homepage.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.thymeleaf.util.MapUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    // 로그인 화면 이동
    @RequestMapping(value="/getLoginPage.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String getLoginPage(HttpServletRequest request, Model model) {
        Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);

        if (!MapUtils.isEmpty(redirectMap)) {
            model.addAttribute("loginVo", redirectMap.get("loginVo"));
        } else {
            LoginVO loginVo = new LoginVO();
            if (!StringUtils.isEmpty((String)request.getAttribute("error"))) {
                loginVo.setResultMessage((String)request.getAttribute("exceptionMsg"));
            }

            model.addAttribute("loginVo", loginVo);
        }

        return "login/login";
    }

    // 로그인 체크
    @PostMapping("/loginCheck.do")
    public String getLoginPage(@ModelAttribute("loginForm") LoginVO loginVO,
                               Model model,
                               @RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "exception", required = false) String exception) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "login/login";
    }

    // 로그아웃
    @GetMapping("/getLogout.do")
    public String getLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return null;
    }

}
