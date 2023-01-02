package anonymous.homepage.login.service;

import anonymous.homepage.login.vo.LoginVO;

import java.util.List;

public interface LoginService {
    public LoginVO selectUserByUsername(LoginVO loginVO);
}
