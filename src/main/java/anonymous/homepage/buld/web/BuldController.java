package anonymous.homepage.buld.web;

import anonymous.homepage.board.vo.BoardVO;
import anonymous.homepage.buld.service.BuldService;
import anonymous.homepage.buld.vo.BuldVO;
import anonymous.homepage.cd.service.CdService;
import anonymous.homepage.file.FileStore;
import anonymous.homepage.file.service.FileService;
import anonymous.homepage.file.vo.AtchFileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/buld")
@RequiredArgsConstructor
public class BuldController {
    private final BuldService buldService;
    private final CdService cdService;
    private final FileStore fileStore;
    private final FileService fileService;

    @GetMapping("/selectBuldList.do")
    public String selectBuldList(Model model) {
        model.addAttribute("buldList", buldService.selectBuldList());
        return "buld/buldList";
    }

    // 매물 등록 화면 이동
    @GetMapping("/registerBuld.do")
    public String registerBoard(Model model) {
        // 거래유형구분코드
        model.addAttribute("delngTySeCds", cdService.selectCdList("A01"));
        // 계약상태구분코드
        model.addAttribute("cntrctSttusSeCds", cdService.selectCdList("A02"));
        // 매물구분코드
        model.addAttribute("saleSeCds", cdService.selectCdList("A03"));

        return "buld/buldRegister";
    }

    // 매물 등록
    @PostMapping("/insertBuld.do")
    @ResponseBody
    public String insertBoard(@ModelAttribute BuldVO buldVO, RedirectAttributes redirect) throws IOException {
        // 첨부파일 등록
        List<AtchFileVO> atchFiles = fileStore.saveFiles(buldVO.getUploadFiles());
        buldVO.setAtchFiles(atchFiles);
        fileService.saveFiles(buldVO);

        buldVO.setAtchDocId(buldVO.getAtchDoc().getAtchDocId());
        buldService.insertBuld(buldVO);

        return "ok";
    }

}
