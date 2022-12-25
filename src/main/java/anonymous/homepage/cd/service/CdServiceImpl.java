package anonymous.homepage.cd.service;

import anonymous.homepage.cd.dao.CdMapper;
import anonymous.homepage.cd.vo.CdVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CdServiceImpl implements CdService {
    private final CdMapper cdMapper;

    @Override
    public List<CdVO> selectCdList(String groupCd) {
        return cdMapper.selectCdList(groupCd);
    }
}
