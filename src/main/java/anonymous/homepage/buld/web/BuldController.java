package anonymous.homepage.buld.web;

import anonymous.homepage.board.vo.BoardVO;
import anonymous.homepage.buld.service.BuldService;
import anonymous.homepage.buld.vo.BuldVO;
import anonymous.homepage.cd.service.CdService;
import anonymous.homepage.file.FileStore;
import anonymous.homepage.file.service.FileService;
import anonymous.homepage.file.vo.AtchDocVO;
import anonymous.homepage.file.vo.AtchFileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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

    // 매물 목록 조회
    @GetMapping("/selectBuldList.do")
    public String selectBuldList(@ModelAttribute BuldVO buldVO, Model model) {
        // 필터 처리
        String originalSelectedSaleSeCds = buldVO.getSelectedSaleSeCds();
        String selectedSaleSeCds = buldVO.getSelectedSaleSeCds();
        if(StringUtils.hasText(selectedSaleSeCds)) {
            selectedSaleSeCds = selectedSaleSeCds.replaceAll(",", "|");
            buldVO.setSelectedSaleSeCds(selectedSaleSeCds);
        }
        
        // 매물 목록 조회
        List<BuldVO> buldList = buldService.selectBuldList(buldVO);

        // 필터 원복
        buldVO.setSelectedSaleSeCds(originalSelectedSaleSeCds);

        // 첨부파일 조회
        for(BuldVO buld : buldList) {
            String atchDocId = buld.getAtchDocId();
            if(StringUtils.hasText(atchDocId)) {
                buld.setAtchFiles(fileService.selectAtchFileList(atchDocId));
            }
        }

        model.addAttribute("buldList", buldList);
        model.addAttribute("buldInfo", buldVO);

        // 매물구분코드
        model.addAttribute("saleSeCds", cdService.selectCdList("A03"));
        
        return "buld/buldList";
    }

    // 매물 목록 조회 - 필터 사용
    @PostMapping("/selectBuldList.do")
    public String selectBuldListWhenUsingFilter(@RequestBody BuldVO buldVO, Model model) {
        // 필터 처리
        String selectedSaleSeCds = buldVO.getSelectedSaleSeCds();
        if(StringUtils.hasText(selectedSaleSeCds)) {
            selectedSaleSeCds = selectedSaleSeCds.replaceAll(",", "|");
            buldVO.setSelectedSaleSeCds(selectedSaleSeCds);
        }
        
        // 매물 목록 조회
        List<BuldVO> buldList = buldService.selectBuldList(buldVO);

        // 첨부파일 조회
        for(BuldVO buld : buldList) {
            String atchDocId = buld.getAtchDocId();
            if(StringUtils.hasText(atchDocId)) {
                buld.setAtchFiles(fileService.selectAtchFileList(atchDocId));
            }
        }

        model.addAttribute("buldList", buldList);

        return "buld/buldList :: #landList";
    }

    // 매물 등록 화면 이동
    @GetMapping("/registerBuld.do")
    public String registerBuld(Model model) {
        // 거래유형구분코드
        model.addAttribute("delngTySeCds", cdService.selectCdList("A01"));
        // 계약상태구분코드
        model.addAttribute("cntrctSttusSeCds", cdService.selectCdList("A02"));
        // 매물구분코드
        model.addAttribute("saleSeCds", cdService.selectCdList("A03"));

        return "buld/buldRegister";
    }

    // 매물 수정 화면 이동
    @GetMapping("/modifyBuld.do")
    public String modifyBuld(Model model) {
        // 거래유형구분코드
        model.addAttribute("delngTySeCds", cdService.selectCdList("A01"));
        // 계약상태구분코드
        model.addAttribute("cntrctSttusSeCds", cdService.selectCdList("A02"));
        // 매물구분코드
        model.addAttribute("saleSeCds", cdService.selectCdList("A03"));

        // 매물 정보
        BuldVO buldVO = new BuldVO();
        buldVO.setBuldNo("000002");
        BuldVO buld = buldService.selectBuld(buldVO);
        String atchDocId = buld.getAtchDocId();
        if(StringUtils.hasText(atchDocId)) {
            buld.setAtchFiles(fileService.selectAtchFileList(atchDocId));
        }
        model.addAttribute("buld", buld);

        return "buld/buldModify";
    }

    // 매물 등록
    @PostMapping("/insertBuld.do")
    @ResponseBody
    public String insertBuld(@ModelAttribute BuldVO buldVO, RedirectAttributes redirect) throws IOException {
        // 첨부파일 등록
        List<AtchFileVO> atchFiles = fileStore.saveFiles(buldVO.getUploadFiles());
        buldVO.setAtchFiles(atchFiles);
        fileService.saveFiles(buldVO);
        if(buldVO.getAtchDoc() != null)
            buldVO.setAtchDocId(buldVO.getAtchDoc().getAtchDocId());

        buldService.insertBuld(buldVO);

        return "ok";
    }

    // 매물 수정
    @PostMapping("/updateBuld.do")
    @ResponseBody
    public String updateBuld(@ModelAttribute BuldVO buldVO, RedirectAttributes redirect) throws IOException {
        // 첨부파일 등록
        List<AtchFileVO> atchFiles = fileStore.saveFiles(buldVO.getUploadFiles());
        buldVO.setAtchFiles(atchFiles);

        String atchDocId = buldVO.getAtchDocId();
        if(StringUtils.hasText(atchDocId)) {
            AtchDocVO atchDocVO = new AtchDocVO();
            atchDocVO.setAtchDocId(atchDocId);
            buldVO.setAtchDoc(atchDocVO);

            fileService.addFiles(buldVO);
        } else {
            fileService.saveFiles(buldVO);
            if(buldVO.getAtchDoc() != null)
                buldVO.setAtchDocId(buldVO.getAtchDoc().getAtchDocId());
        }

        buldService.updateBuld(buldVO);

        return "ok";
    }

}
