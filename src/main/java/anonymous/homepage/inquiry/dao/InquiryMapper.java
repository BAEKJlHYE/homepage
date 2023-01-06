package anonymous.homepage.inquiry.dao;

import anonymous.homepage.inquiry.vo.InquiryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {
    public List<InquiryVO> selectInquiryList(InquiryVO inquiryVO);

    public int selectInquiryCount(InquiryVO inquiryVO);

    public InquiryVO selectInquiry(InquiryVO inquiryVO);

    public int insertInquiry(InquiryVO inquiryVO);
}
