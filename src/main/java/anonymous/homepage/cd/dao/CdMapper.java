package anonymous.homepage.cd.dao;

import anonymous.homepage.cd.vo.CdVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CdMapper {
    public List<CdVO> selectCdList(String groupCd);
}
