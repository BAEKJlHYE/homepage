package anonymous.homepage.login.config;

import anonymous.homepage.login.dao.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

//    @Autowired
//    private AdminRepository adminRepository;
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = loginMapper.selectUserId(username);
        if(userDetails == null) {
            throw new UsernameNotFoundException("유효하지 않는 로그인 정보입니다.");
        }

        return userDetails;
    }

}
