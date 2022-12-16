package anonymous.homepage.join.web;

import anonymous.homepage.board.service.BoardService;
import anonymous.homepage.join.service.JoinService;
import anonymous.homepage.join.service.JoinServiceImpl;
import anonymous.homepage.join.vo.JoinVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinController {
    private final JoinService joinService;

    @RequestMapping("/joinTerm")
    public ModelAndView joinTerm(){

        ModelAndView mav=new ModelAndView();
        mav.setViewName("joinTerm");
        return mav;
    }

    @RequestMapping("/signUp")
    public ModelAndView signup(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("signUp");
        return mav;
    }

    @RequestMapping("/insertMemberInfo")
    public ModelAndView signUpCheck(){

        ModelAndView mav=new ModelAndView();
//        joinService.insertMemberInfo();
        mav.setViewName("insertMemberInfo");
        return mav;
    }

    @RequestMapping("/idValidAjax")
    @ResponseBody
    public Object idValidAjax(){

        JoinVO joinVO=new JoinVO();
        List<JoinVO> list= joinService.getIdValidAjax(joinVO);
        return list;
    }

    @RequestMapping("/pwValidAjax")
    @ResponseBody
    public Object pwValidAjax(JoinVO joinVO) {

        List<JoinVO> list= joinService.getPWValidAjax(joinVO);
        return list;
    }

    @RequestMapping("/nameValidAjax")
    @ResponseBody
    public Object nameValidAjax(JoinVO joinVO){

        List<JoinVO> list= joinService.getNameValidAjax(joinVO);
        return list;
    }

    @RequestMapping("/nickValidAjax")
    @ResponseBody
    public Object nickValidAjax(JoinVO joinVO){

        List<JoinVO> list= joinService.getNickValidAjax(joinVO);
        return list;
    }

    @RequestMapping("/emailValidAjax")
    @ResponseBody
    public Object emailValidAjax(JoinVO joinVO){

        List<JoinVO> list= joinService.getEmailValidAjax(joinVO);
        return list;
    }


}