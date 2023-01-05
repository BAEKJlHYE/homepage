package anonymous.homepage.inquiry.service;

import anonymous.homepage.inquiry.dao.InquiryMapper;
import anonymous.homepage.inquiry.vo.InquiryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {
    private final InquiryMapper inquiryMapper;

    @Override
    public int insertInquiry(InquiryVO inquiryVO) {
        return inquiryMapper.insertInquiry(inquiryVO);
    }
}
