package anonymous.homepage.login.service;

import anonymous.homepage.login.dao.LoginMapper;
import anonymous.homepage.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final LoginMapper LoginMapper;

    public LoginVO selectUser(LoginVO loginVO) {
        return LoginMapper.selectUser(loginVO);
    }

}
