package anonymous.homepage.join.service;


import anonymous.homepage.board.dao.BoardMapper;
import anonymous.homepage.board.vo.BoardVO;
import anonymous.homepage.join.dao.JoinMapper;
import anonymous.homepage.join.vo.JoinVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService{

    private final JoinMapper joinMapper;

    @Override
    public int insertMemberInfo(JoinVO joinVO) {
        return joinMapper.insertMemberInfo(joinVO);
    }

    @Override
    public List<JoinVO> getIdValidAjax(JoinVO joinVO) {
        return joinMapper.getIdValidAjax(joinVO);
    }

    @Override
    public List<JoinVO> getPWValidAjax(JoinVO joinVO) {
        return joinMapper.getPWValidAjax(joinVO);
    }

    @Override
    public List<JoinVO> getNameValidAjax(JoinVO joinVO) {
        return joinMapper.getNameValidAjax(joinVO);
    }

    @Override
    public List<JoinVO> getNickValidAjax(JoinVO joinVO) {
        return joinMapper.getNickValidAjax(joinVO);
    }

    @Override
    public List<JoinVO> getEmailValidAjax(JoinVO joinVO) {
        return joinMapper.getEmailValidAjax(joinVO);
    }
}
