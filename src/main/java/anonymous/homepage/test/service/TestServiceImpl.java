package anonymous.homepage.test.service;

import anonymous.homepage.test.dao.TestMapper;
import anonymous.homepage.test.vo.TestVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestMapper testMapper;

    @Override
    public List<TestVO> selectTestList() {
        return testMapper.selectTestList();
    }
}
