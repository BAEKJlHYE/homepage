package anonymous.homepage.test.dao;

import anonymous.homepage.test.vo.TestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestVO> selectTestList();
}
