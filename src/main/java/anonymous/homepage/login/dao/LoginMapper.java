package anonymous.homepage.login.dao;

import anonymous.homepage.login.vo.LoginVO;
import anonymous.homepage.login.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Mapper
public interface LoginMapper {
    LoginVO selectUser(LoginVO loginVO);

    UserDetails selectUserId(String id);
}
