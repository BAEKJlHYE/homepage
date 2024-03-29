package anonymous.homepage.file.service;

import anonymous.homepage.file.vo.AtchFileVO;
import anonymous.homepage.vo.BaseVO;

import java.util.List;

public interface FileService {
    public List<AtchFileVO> selectAtchFileList(String atchDocId);

    public AtchFileVO selectAtchFile(String atchFileId);

    public void saveFiles(BaseVO baseVO);

    public void addFiles(BaseVO baseVO);

    public void deleteAtchFile(String atchFileId);
}
