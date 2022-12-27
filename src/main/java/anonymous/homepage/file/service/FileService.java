package anonymous.homepage.file.service;

import anonymous.homepage.file.vo.AtchFileVO;
import anonymous.homepage.vo.BaseVO;

import java.util.List;

public interface FileService {
    public List<AtchFileVO> selectAtchFileList(String atchDocId);
    public void saveFiles(BaseVO baseVO);
}
