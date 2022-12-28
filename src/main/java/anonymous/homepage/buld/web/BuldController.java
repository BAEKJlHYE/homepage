package anonymous.homepage.buld.web;

import anonymous.homepage.buld.service.BuldService;
import anonymous.homepage.buld.vo.BuldVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/buld")
@RequiredArgsConstructor
public class BuldController {
    private final BuldService buldService;

    @GetMapping("/selectBuldList.do")
    public String selectBuldList(Model model) {
        model.addAttribute("buldList", buldService.selectBuldList());
        return "buld/buldList";
    }

}
