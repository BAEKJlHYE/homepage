package anonymous.homepage.directDeal.vo;

import anonymous.homepage.vo.BaseVO;
import lombok.Data;

@Data
public class DirectDealVO extends BaseVO {
    private int boardId;
    private String title;
    private String content;
    private String writerNm;
    private String secretYn;
    private String password;
    private String headerSeCd;
    private String registDt;
    private String updateNm;
    private String updateDt;
}
