package anonymous.homepage.main.dao;

import anonymous.homepage.login.vo.LoginVO;
import anonymous.homepage.main.vo.MainVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface MainMapper {
    MainVO selectMainList(MainVO mainVO);
}
