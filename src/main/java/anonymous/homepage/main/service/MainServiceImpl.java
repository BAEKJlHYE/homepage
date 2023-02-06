package anonymous.homepage.main.service;

import anonymous.homepage.buld.vo.BuldVO;
import anonymous.homepage.main.dao.MainMapper;
import anonymous.homepage.main.vo.MainVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
    private final MainMapper mainMapper;

    public BuldVO selectMainBuldDetail(MainVO mainVO) {
        return mainMapper.selectMainBuldDetail(mainVO);
    }

}
