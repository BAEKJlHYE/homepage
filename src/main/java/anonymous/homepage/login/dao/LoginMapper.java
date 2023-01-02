package anonymous.homepage.login.dao;

import anonymous.homepage.login.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {
    LoginVO selectUserAccount(LoginVO loginVO);
}
