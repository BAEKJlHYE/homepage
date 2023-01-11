package anonymous.homepage.login.service;

import anonymous.homepage.login.vo.LoginVO;

public interface LoginService {
    public LoginVO selectUser(LoginVO loginVO);
}
