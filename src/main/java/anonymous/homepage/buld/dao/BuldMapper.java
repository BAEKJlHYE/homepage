package anonymous.homepage.buld.dao;

import anonymous.homepage.buld.vo.BuldVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuldMapper {
    public List<BuldVO> selectBuldList(BuldVO buldVO);

    public BuldVO selectBuld(BuldVO buldVO);

    public int insertBuld(BuldVO buldVO);

    public int updateBuld(BuldVO buldVO);
}
