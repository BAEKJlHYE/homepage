package anonymous.homepage.main.service;

import anonymous.homepage.login.dao.LoginMapper;
import anonymous.homepage.login.service.LoginService;
import anonymous.homepage.login.vo.LoginVO;
import anonymous.homepage.main.vo.MainVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {
//    private final LoginMapper LoginMapper;

    public MainVO selectMain(MainVO mainVO) {
        return null;
    }

}
