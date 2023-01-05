package anonymous.homepage.inquiry.vo;

import anonymous.homepage.vo.BaseVO;
import lombok.Data;

@Data
public class InquiryVO extends BaseVO {
    private int boardId;
    private String title;
    private String content;
    private String writerNm;
    private String secretYn;
    private String password;
    private String headerSeCd;
    private String headerSeNm;
    private String telNo;
    private String answerYn;
    private String atchDocId;
    private String registDt;
    private String displayRegistDt;
    private String updateNm;
    private String updateDt;
}
