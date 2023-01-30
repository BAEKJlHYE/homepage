package anonymous.homepage.buld.service;

import anonymous.homepage.buld.vo.BuldVO;

import java.util.List;

public interface BuldService {
    public List<BuldVO> selectBuldList();

    public int insertBoard(BuldVO buldVO);
}
