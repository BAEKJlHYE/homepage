package anonymous.homepage.cntrctReview.dao;

import anonymous.homepage.cntrctReview.vo.CntrctReviewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CntrctReviewMapper {
    public List<CntrctReviewVO> selectBoardList(CntrctReviewVO cntrctReviewVO);
    public CntrctReviewVO selectBoard(CntrctReviewVO cntrctReviewVO);
    public int selectBoardCount(CntrctReviewVO cntrctReviewVO);
    public int insertBoard(CntrctReviewVO cntrctReviewVO);
    public int updateBoard(CntrctReviewVO cntrctReviewVO);
    public int deleteBoard(CntrctReviewVO cntrctReviewVO);
    public int updateBoardViewCount(CntrctReviewVO cntrctReviewVO);
    public int updateBoardSuggestionCount(CntrctReviewVO cntrctReviewVO);
}
