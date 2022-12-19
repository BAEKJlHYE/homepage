package anonymous.homepage.join.dao;

import anonymous.homepage.join.vo.JoinVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JoinMapper {

    public int insertMemberInfo(JoinVO joinVO);

    public List<JoinVO> getIdValidAjax(JoinVO joinVO);

    public List<JoinVO> getPWValidAjax(JoinVO joinVO);

    public List<JoinVO> getNameValidAjax(JoinVO joinVO);

    public List<JoinVO> getNickValidAjax(JoinVO joinVO);

    public List<JoinVO> getEmailValidAjax(JoinVO joinVO);
}
