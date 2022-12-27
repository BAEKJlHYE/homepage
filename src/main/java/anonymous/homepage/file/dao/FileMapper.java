package anonymous.homepage.file.dao;

import anonymous.homepage.file.vo.AtchDocVO;
import anonymous.homepage.file.vo.AtchFileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    public int insertAtchDoc(AtchDocVO atchDocVO);

    public int insertAtchFile(AtchFileVO atchFileVO);
}
