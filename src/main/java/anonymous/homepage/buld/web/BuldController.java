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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        model.addAttribute("buldDetail", new BuldVO());

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

    // 매물 상세 조회
    @PostMapping("/selectBuld.do")
    public String selectBuld(@RequestBody BuldVO buldVO, Model model) {
        BuldVO buldDetail = buldService.selectBuld(buldVO);
        String atchDocId = buldDetail.getAtchDocId();
        if(StringUtils.hasText(atchDocId)) {
            buldDetail.setAtchFiles(fileService.selectAtchFileList(atchDocId));
        }

        model.addAttribute("buldDetail", buldDetail);
        return "buld/buldList :: #exPop";
    }

    // 매물 등록 화면 이동
    @GetMapping("/registerBuld.do")
    public String registerBuld(@ModelAttribute BuldVO buldVO, Model model) {
        // 거래유형구분코드
        model.addAttribute("delngTySeCds", cdService.selectCdList("A01"));
        // 계약상태구분코드
        model.addAttribute("cntrctSttusSeCds", cdService.selectCdList("A02"));
        // 매물구분코드
        model.addAttribute("saleSeCds", cdService.selectCdList("A03"));

        model.addAttribute("buldInfo", buldVO);

        return "buld/buldRegister";
    }

    // 매물 수정 화면 이동
    @GetMapping("/modifyBuld.do")
    public String modifyBuld(@ModelAttribute BuldVO buldVO, Model model) {
        // 거래유형구분코드
        model.addAttribute("delngTySeCds", cdService.selectCdList("A01"));
        // 계약상태구분코드
        model.addAttribute("cntrctSttusSeCds", cdService.selectCdList("A02"));
        // 매물구분코드
        model.addAttribute("saleSeCds", cdService.selectCdList("A03"));

        // 매물 정보
        BuldVO buld = buldService.selectBuld(buldVO);
        String atchDocId = buld.getAtchDocId();
        if(StringUtils.hasText(atchDocId)) {
            buld.setAtchFiles(fileService.selectAtchFileList(atchDocId));
        }
        model.addAttribute("buld", buld);
        model.addAttribute("buldInfo", buldVO);

        return "buld/buldModify";
    }

    // 매물 등록
    @PostMapping("/insertBuld.do")
    public String insertBuld(@ModelAttribute BuldVO buldVO, RedirectAttributes redirect) throws IOException {
        String resultMessage;
        if(buldVO.getIsPermitted()) {
            // 첨부파일 등록
            List<AtchFileVO> atchFiles = fileStore.saveFiles(buldVO.getUploadFiles());
            buldVO.setAtchFiles(atchFiles);
            fileService.saveFiles(buldVO);
            if(buldVO.getAtchDoc() != null)
                buldVO.setAtchDocId(buldVO.getAtchDoc().getAtchDocId());

            buldService.insertBuld(buldVO);

            resultMessage = "매물이 등록되었습니다.";
        } else {
            resultMessage = "권한이 없습니다.";
        }

        buldVO.setResultMessage(resultMessage);
        redirect.addFlashAttribute("buldVO", buldVO);
        return "redirect:/buld/selectBuldList.do";
    }

    // 매물 수정
    @PostMapping("/updateBuld.do")
    public String updateBuld(@ModelAttribute BuldVO buldVO, RedirectAttributes redirect) throws IOException {
        String resultMessage;
        if(buldVO.getIsPermitted()) {
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

            resultMessage = "매물 정보가 수정되었습니다.";
        } else {
            resultMessage = "권한이 없습니다.";
        }

        buldVO.setResultMessage(resultMessage);
        redirect.addFlashAttribute("buldVO", buldVO);
        return "redirect:/buld/selectBuldList.do";
    }

    // 매물 삭제
    @PostMapping("/deleteBuld.do")
    @ResponseBody
    public Map<String, Object> deleteBuld(@RequestBody BuldVO buldVO) throws IOException {
        String resultCode;
        String resultMessage;

        if(buldVO.getIsPermitted()) {
            BuldVO buld = buldService.selectBuld(buldVO);
            String atchDocId = buld.getAtchDocId();
            if(StringUtils.hasText(atchDocId)) {
                List<AtchFileVO> atchFileVOs = fileService.selectAtchFileList(atchDocId);
                for(AtchFileVO atchFileVO : atchFileVOs) {
                    fileService.deleteAtchFile(atchFileVO.getAtchFileId());
                }
            }

            buldService.deleteBuld(buldVO);

            resultCode = "000";
            resultMessage = "매물이 삭제되었습니다.";
        } else {
            resultCode = "999";
            resultMessage = "권한이 없습니다.";
        }

        Map<String, Object> result = new HashMap<>();
        result.put("resultMessage", resultMessage);
        result.put("resultCode", resultCode);

        return result;
    }
}
