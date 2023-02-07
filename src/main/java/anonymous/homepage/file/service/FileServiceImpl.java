package anonymous.homepage.file.service;

import anonymous.homepage.file.FileStore;
import anonymous.homepage.file.dao.FileMapper;
import anonymous.homepage.file.vo.AtchDocVO;
import anonymous.homepage.file.vo.AtchFileVO;
import anonymous.homepage.vo.BaseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileStore fileStore;
    private final FileMapper fileMapper;

    @Override
    public List<AtchFileVO> selectAtchFileList(String atchDocId) {
        return fileMapper.selectAtchFileList(atchDocId);
    }

    @Override
    public AtchFileVO selectAtchFile(String atchFileId) {
        return fileMapper.selectAtchFile(atchFileId);
    }

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

    @Override
    public void addFiles(BaseVO baseVO) {
        if(baseVO.getAtchFiles() == null || baseVO.getAtchFiles().size() == 0) {
            return;
        }

        String userId = baseVO.getUserId();
        for(AtchFileVO atchFileVO : baseVO.getAtchFiles()) {
            atchFileVO.setAtchDocId(baseVO.getAtchDoc().getAtchDocId());
            atchFileVO.setRegistId(userId);
            atchFileVO.setUpdateId(userId);
            fileMapper.insertAtchFile(atchFileVO);
        }
    }

    @Override
    public void deleteAtchFile(String atchFileId) {
        AtchFileVO atchFile = fileMapper.selectAtchFile(atchFileId);
        fileStore.deleteFile(atchFile.getFileNm());
        fileMapper.deleteAtchFile(atchFileId);
    }
}
