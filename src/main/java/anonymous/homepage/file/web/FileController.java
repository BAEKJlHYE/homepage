package anonymous.homepage.file.web;

import anonymous.homepage.file.FileStore;
import anonymous.homepage.file.service.FileService;
import anonymous.homepage.file.vo.AtchFileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {
    private final FileStore fileStore;
    private final FileService fileService;

    @GetMapping("/download/{atchFileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String atchFileId) throws MalformedURLException {
        AtchFileVO atchFileVO = fileService.selectAtchFile(atchFileId);

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(atchFileVO.getFileNm()));
        String encodedOriginalFileName = UriUtils.encode(atchFileVO.getOrginlFileNm(), StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedOriginalFileName + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    @GetMapping("/image/{atchFileId}")
    @ResponseBody
    public Resource viewImage(@PathVariable String atchFileId) throws MalformedURLException {
        AtchFileVO atchFileVO = fileService.selectAtchFile(atchFileId);
        return new UrlResource("file:" + fileStore.getFullPath(atchFileVO.getFileNm()));
    }

    @PostMapping("/deleteAtchDoc/{atchDocId}")
    @ResponseBody
    public String deleteAtchDoc(@PathVariable String atchDocId) {
        fileService.deleteAtchDoc(atchDocId);
        return "okay";
    }

    @PostMapping("/deleteAtchFile/{atchFileId}")
    @ResponseBody
    public String deleteAtchFile(@PathVariable String atchFileId) {
        fileService.deleteAtchFile(atchFileId);
        return "okay";
    }
}
