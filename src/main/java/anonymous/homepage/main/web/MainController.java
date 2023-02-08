package anonymous.homepage.main.web;

import anonymous.homepage.buld.service.BuldService;
import anonymous.homepage.buld.vo.BuldVO;
import anonymous.homepage.file.service.FileService;
import anonymous.homepage.main.service.MainService;
import anonymous.homepage.main.vo.MainVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.thymeleaf.util.MapUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
    private final BuldService buldService;
    private final MainService mainService;
    private final FileService fileService;

    // 메인 화면 이동
    @GetMapping("/main.do")
    public String getMainPage(HttpServletRequest request, Model model) {
        Map<String, ?> redirectMap = RequestContextUtils.getInputFlashMap(request);
        if (!MapUtils.isEmpty(redirectMap)) {
            model.addAttribute(redirectMap.get("loginVo"));
        }

        List<BuldVO> buldList = buldService.selectBuldList();
        for(BuldVO buldVO : buldList) {
            String atchDocId = buldVO.getAtchDocId();
            if(StringUtils.hasText(atchDocId)) {
                buldVO.setAtchFiles(fileService.selectAtchFileList(atchDocId));
            }
        }

        model.addAttribute("buldList", buldList);
        model.addAttribute("buldDetail", new BuldVO());
        return "main/main";
    }

    // 메인 매물 상세 조회
    @RequestMapping(value = "/selectMainBuldDetail.do", method = { RequestMethod.POST })
    public String selectMainBuldDetail(@ModelAttribute("buldDetailForm") MainVO mainVO, Model model) {
        BuldVO buldVO = mainService.selectMainBuldDetail(mainVO);
        String atchDocId = buldVO.getAtchDocId();

        if (StringUtils.hasText(atchDocId)) {
            buldVO.setAtchFiles(fileService.selectAtchFileList(atchDocId));
        }

        model.addAttribute("buldDetail", buldVO);
        return "main/main :: #exPop";
    }
}
