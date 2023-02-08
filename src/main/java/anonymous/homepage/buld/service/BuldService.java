package anonymous.homepage.buld.service;

import anonymous.homepage.buld.vo.BuldVO;

import java.util.List;

public interface BuldService {
    public List<BuldVO> selectBuldList(BuldVO buldVO);

    public BuldVO selectBuld(BuldVO buldVO);

    public int insertBuld(BuldVO buldVO);

    public int updateBuld(BuldVO buldVO);
}
