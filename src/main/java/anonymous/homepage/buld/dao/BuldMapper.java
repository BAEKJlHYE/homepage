package anonymous.homepage.buld.dao;

import anonymous.homepage.buld.vo.BuldVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuldMapper {
    public List<BuldVO> selectBuldList();
}
