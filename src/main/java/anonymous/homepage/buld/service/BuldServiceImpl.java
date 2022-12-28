package anonymous.homepage.buld.service;

import anonymous.homepage.buld.dao.BuldMapper;
import anonymous.homepage.buld.vo.BuldVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuldServiceImpl implements BuldService {
    private final BuldMapper buldMapper;

    public List<BuldVO> selectBuldList() {
        return buldMapper.selectBuldList();
    }
}
