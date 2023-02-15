package anonymous.homepage.inquiry.service;

import anonymous.homepage.inquiry.dao.InquiryMapper;
import anonymous.homepage.inquiry.vo.InquiryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryMapper inquiryMapper;

    @Override
    public List<InquiryVO> selectInquiryList(InquiryVO inquiryVO) {
        return inquiryMapper.selectInquiryList(inquiryVO);
    }

    @Override
    public int selectInquiryCount(InquiryVO inquiryVO) {
        return inquiryMapper.selectInquiryCount(inquiryVO);
    }

    @Override
    public InquiryVO selectInquiry(InquiryVO inquiryVO) {
        return inquiryMapper.selectInquiry(inquiryVO);
    }

    @Override
    public int checkPassword(InquiryVO inquiryVO) {
        return inquiryMapper.checkPassword(inquiryVO);
    }

    @Override
    public int insertInquiry(InquiryVO inquiryVO) {
        return inquiryMapper.insertInquiry(inquiryVO);
    }

    @Override
    public int changeAnswerYn(InquiryVO inquiryVO) {
        return inquiryMapper.changeAnswerYn(inquiryVO);
    }

    @Override
    public int deleteInquiry(InquiryVO inquiryVO) { return inquiryMapper.deleteInquiry(inquiryVO); }
}
