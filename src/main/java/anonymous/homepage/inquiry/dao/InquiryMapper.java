package anonymous.homepage.inquiry.dao;

import anonymous.homepage.inquiry.vo.InquiryVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InquiryMapper {
    public int insertInquiry(InquiryVO inquiryVO);
}
