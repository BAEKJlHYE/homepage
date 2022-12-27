package anonymous.homepage.file.service;

import anonymous.homepage.file.dao.FileMapper;
import anonymous.homepage.file.vo.AtchDocVO;
import anonymous.homepage.file.vo.AtchFileVO;
import anonymous.homepage.vo.BaseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileMapper fileMapper;

    @Override
    public void saveFiles(BaseVO baseVO) {
        if(baseVO.getAtchFiles() == null || baseVO.getAtchFiles().size() == 0) {
            return;
        }

        baseVO.setAtchDoc(new AtchDocVO());
        AtchDocVO atchDocVO = baseVO.getAtchDoc();
        String userId = baseVO.getUserId();

        // 첨부문서 등록
        atchDocVO.setRegistId(userId);
        atchDocVO.setUpdateId(userId);
        fileMapper.insertAtchDoc(atchDocVO);

        // 첨부파일 등록
        for(AtchFileVO atchFileVO : baseVO.getAtchFiles()) {
            atchFileVO.setRegistId(userId);
            atchFileVO.setUpdateId(userId);
            atchFileVO.setAtchDocId(atchDocVO.getAtchDocId());
            fileMapper.insertAtchFile(atchFileVO);
        }
    }
}
