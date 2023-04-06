package anonymous.homepage.cntrctReview.service;

import anonymous.homepage.cntrctReview.dao.CntrctReviewMapper;
import anonymous.homepage.cntrctReview.vo.CntrctReviewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CntrctReviewImpl implements CntrctReviewService {
    private final CntrctReviewMapper cntrctReviewMapper;

    public List<CntrctReviewVO> selectBoardList(CntrctReviewVO cntrctReviewVO) {
        return cntrctReviewMapper.selectBoardList(cntrctReviewVO);
    }

    public CntrctReviewVO selectBoard(CntrctReviewVO cntrctReviewVO) {
        return cntrctReviewMapper.selectBoard(cntrctReviewVO);
    }

    public int selectBoardCount(CntrctReviewVO cntrctReviewVO) {
        return cntrctReviewMapper.selectBoardCount(cntrctReviewVO);
    }

    public int insertBoard(CntrctReviewVO cntrctReviewVO) {
        return cntrctReviewMapper.insertBoard(cntrctReviewVO);
    }

    public int updateBoard(CntrctReviewVO cntrctReviewVO) {
        return cntrctReviewMapper.updateBoard(cntrctReviewVO);
    }

    public int deleteBoard(CntrctReviewVO cntrctReviewVO) {
        return cntrctReviewMapper.deleteBoard(cntrctReviewVO);
    }

    public int updateBoardViewCount(CntrctReviewVO cntrctReviewVO) {
        return cntrctReviewMapper.updateBoardViewCount(cntrctReviewVO);
    }

    public int updateBoardSuggestionCount(CntrctReviewVO cntrctReviewVO) {
        return cntrctReviewMapper.updateBoardSuggestionCount(cntrctReviewVO);
    }
}
