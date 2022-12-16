package anonymous.homepage.join.vo;

import lombok.Data;

@Data
public class JoinVO {

    private String USERID;
    private String PASSWD;
    private String USERNM;
    private String NICK;
    private String EMAIL;
    
    //메일 수신 동의
    private int MRECAGR;
    
    //정보 공개 동의
    private int IPUBAGR;

}
