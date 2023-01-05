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
    private String telNo;
    private String answerYn;
    private String registDt;
    private String updateNm;
    private String updateDt;
}
