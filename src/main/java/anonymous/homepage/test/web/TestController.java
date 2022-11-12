package anonymous.homepage.test.web;

import anonymous.homepage.test.service.TestService;
import anonymous.homepage.test.vo.TestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/test")
    public List<TestVO> selectTestList() {
        return testService.selectTestList();
    }
}
