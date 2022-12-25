package anonymous.homepage.board.vo;

import anonymous.homepage.vo.BaseVO;
import lombok.Data;

@Data
public class BoardVO extends BaseVO {
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private int boardOrder;
    private String registDt;
    private String displayRegistDt;
    private int suggestionCount;
    private int viewCount;
}
