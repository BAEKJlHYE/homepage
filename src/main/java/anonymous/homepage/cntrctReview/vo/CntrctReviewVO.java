package anonymous.homepage.cntrctReview.vo;

import anonymous.homepage.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CntrctReviewVO extends BaseVO {
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private int boardOrder;
    private String registDt;
    private String displayRegistDt;
    private int suggestionCount;
    private int viewCount;
    private String headerSeCd;
    private String headerSeNm;
    private int nextBoardId;
    private String nextBoardTitle;
    private int prevBoardId;
    private String prevBoardTitle;
}
