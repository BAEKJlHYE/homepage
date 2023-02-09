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

    @Override
    public List<BuldVO> selectBuldList(BuldVO buldVO) {
        return buldMapper.selectBuldList(buldVO);
    }

    @Override
    public BuldVO selectBuld(BuldVO buldVO) {
        return buldMapper.selectBuld(buldVO);
    }

    @Override
    public int insertBuld(BuldVO buldVO) {
        return buldMapper.insertBuld(buldVO);
    }

    @Override
    public int updateBuld(BuldVO buldVO) {
        return buldMapper.updateBuld(buldVO);
    }

    @Override
    public int deleteBuld(BuldVO buldVO) {
        return buldMapper.deleteBuld(buldVO);
    }
}
