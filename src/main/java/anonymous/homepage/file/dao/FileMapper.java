package anonymous.homepage.file.dao;

import anonymous.homepage.file.vo.AtchDocVO;
import anonymous.homepage.file.vo.AtchFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    public List<AtchFileVO> selectAtchFileList(String atchDocId);

    public AtchFileVO selectAtchFile(String atchFileId);

    public int insertAtchDoc(AtchDocVO atchDocVO);

    public int insertAtchFile(AtchFileVO atchFileVO);
}
