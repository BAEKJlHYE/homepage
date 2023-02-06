package anonymous.homepage.main.dao;

import anonymous.homepage.buld.vo.BuldVO;
import anonymous.homepage.main.vo.MainVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainMapper {
    BuldVO selectMainBuldDetail(MainVO mainVO);
}
