package anonymous.homepage.inquiry.service;

import anonymous.homepage.inquiry.vo.InquiryVO;

import java.util.List;

public interface InquiryService {
    public List<InquiryVO> selectInquiryList(InquiryVO inquiryVO);

    public int selectInquiryCount(InquiryVO inquiryVO);

    public InquiryVO selectInquiry(InquiryVO inquiryVO);

    public int checkPassword(InquiryVO inquiryVO);

    public int insertInquiry(InquiryVO inquiryVO);

    public int changeAnswerYn(InquiryVO inquiryVO);
}
