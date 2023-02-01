package anonymous.homepage.login.web;

import anonymous.homepage.login.service.LoginService;
import anonymous.homepage.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.thymeleaf.util.MapUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    // 로그인 체크
    @GetMapping("/loginCheck.do")
    public String getLoginPage(@ModelAttribute("loginForm") LoginVO loginVO,
                               Model model,
                               @RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "exception", required = false) String exception) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        if (StringUtils.isEmpty(error)) {
            System.out.println("error : NO"+ error);
            System.out.println("exception : NO"+ exception);
        } else {
            System.out.println("error : YES"+ error);
            System.out.println("exception : YES"+ exception);
        }

        return "login/login";
    }

    // 로그아웃
    @GetMapping("/getLogout.do")
//    public String getLogout(HttpServletRequest request, Model model) {
    public String getLogout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "redirect:/";

//        return "main/main.do";
    }

}
