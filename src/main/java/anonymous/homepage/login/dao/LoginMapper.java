package anonymous.homepage.login.dao;

import anonymous.homepage.login.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface LoginMapper {
    LoginVO selectUser(LoginVO loginVO);

    UserDetails selectUserId(String id);
}
