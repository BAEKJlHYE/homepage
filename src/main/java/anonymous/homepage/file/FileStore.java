package anonymous.homepage.file;

import anonymous.homepage.file.vo.AtchFileVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.directory}")
    private String directory;

    public String getFullPath(String fileName) {
        // 폴더 없을 경우 만드는 걸로 수정
        // 폴더 경로에 날짜 추가
        return directory + fileName;
    }

    public List<AtchFileVO> saveFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<AtchFileVO> atchFiles = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()) {
                atchFiles.add(saveFile(multipartFile));
            }
        }

        return atchFiles;
    }

    public AtchFileVO saveFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String fileNameForSaving = createFileNameForSaving(originalFileName);

        multipartFile.transferTo(new File(getFullPath(fileNameForSaving)));

        AtchFileVO atchFile = new AtchFileVO();
        atchFile.setFileNm(fileNameForSaving);
        atchFile.setOrginlFileNm(originalFileName);
        atchFile.setFileSize(multipartFile.getSize());
        return atchFile;
    }

    private String createFileNameForSaving(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFileName);
        return uuid + "." + ext;
    }

    private String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }
}
