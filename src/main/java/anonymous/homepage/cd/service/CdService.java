package anonymous.homepage.cd.service;

import anonymous.homepage.cd.vo.CdVO;

import java.util.List;

public interface CdService {
    public List<CdVO> selectCdList(String groupCd);
}
