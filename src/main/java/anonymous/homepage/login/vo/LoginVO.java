package anonymous.homepage.login.vo;

import anonymous.homepage.vo.BaseVO;
import lombok.Data;

@Data
public class LoginVO {
    private static final long serialVersionUID = 1L;
    private String resultMessage;
    private String userId;
    private String userPw;
    private String userNm;
    private String userBirth;
    private String userPhone;
    private String registId;
    private String registDt;
    private String updateId;
    private String updateDt;
    private String userEmail;
}
