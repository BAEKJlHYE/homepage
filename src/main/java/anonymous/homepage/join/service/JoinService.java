package anonymous.homepage.join.service;

import anonymous.homepage.board.vo.BoardVO;
import anonymous.homepage.join.vo.JoinVO;

import java.util.List;

public interface JoinService {

    public int insertMemberInfo(JoinVO joinVO);

    public List<JoinVO> getIdValidAjax(JoinVO joinVO);

    public List<JoinVO> getPWValidAjax(JoinVO joinVO);

    public List<JoinVO> getNameValidAjax(JoinVO joinVO);

    public List<JoinVO> getNickValidAjax(JoinVO joinVO);

    public List<JoinVO> getEmailValidAjax(JoinVO joinVO);

}
