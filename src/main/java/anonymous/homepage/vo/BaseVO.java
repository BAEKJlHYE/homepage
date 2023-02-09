package anonymous.homepage.vo;

import anonymous.homepage.file.vo.AtchDocVO;
import anonymous.homepage.file.vo.AtchFileVO;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.Collection;
import java.util.List;

@Data
public class BaseVO {
    /* 사용자 정보 */
    private String userId = "admin";    // 사용자 ID (로그인 시 저장하는 것으로 수정 필요)
    private boolean isPermitted;        // 권한 존재 여부

    public String getUserId() {
        this.userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.userId;
    }

    public boolean getIsPermitted() {
        this.isPermitted = false;
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for(GrantedAuthority authority : authorities) {
            if(StringUtils.equals(authority.getAuthority(), "ROLE_ADMIN"))
                this.isPermitted = true;
        }

        return this.isPermitted;
    }

    /* 처리 결과 */
    private String resultMessage;

    /* 검색 */
    private String searchSeCd;          // 검색 구분
    private String searchKeyword;       // 검색어

    /* 첨부파일 */
    private List<MultipartFile> uploadFiles;
    private AtchDocVO atchDoc;
    private List<AtchFileVO> atchFiles;

    /* 페이징 */
    private int recordCount;            // 게시글 개수
    private int recordUnit;             // 한 페이지 당 게시할 게시글 개수
    private int firstRecordNumber;      // 시작 rownum
    private int lastRecordNumber;       // 종료 rownum
    private int nowPage;                // 현재 페이지
    private int pageUnit;               // 페이지에 게시할 페이지 개수
    private int pageCount;              // 총 페이지 개수
    private int firstPage;              // 현재 페이지의 첫 번째 페이지
    private int lastPage;               // 현재 페이지의 마지막 페이지
    private boolean prevAllPageExist;   // '<<' 표시 여부
    private boolean nextAllPageExist;   // '>>' 표시 여부
    private boolean prevPageExist;      // '<' 표시 여부
    private boolean nextPageExist;      // '>' 표시 여부

    public int getFirstRecordNumber() {
        this.firstRecordNumber = this.recordUnit * (this.nowPage - 1) + 1;
        return this.firstRecordNumber;
    }

    public int getLastRecordNumber() {
        this.lastRecordNumber = this.recordUnit * this.nowPage;
        return this.lastRecordNumber;
    }

    public int getPageCount() {
        var share = this.recordCount / this.recordUnit;
        if(this.recordCount % this.recordUnit > 0)
            share += 1;

        this.pageCount = share;
        return this.pageCount;
    }

    public int getFirstPage() {
        int share = this.nowPage / this.pageUnit;
        if(this.nowPage % this.pageUnit == 0)
            share -= 1;

        this.firstPage = share * this.pageUnit + 1;
        return this.firstPage;
    }

    public int getLastPage() {
        int share = this.nowPage / this.pageUnit;
        if(this.nowPage % this.pageUnit == 0)
            share -= 1;

        this.lastPage = (share + 1) * this.pageUnit;
        if(getPageCount() < lastPage)
            this.lastPage = getPageCount() == 0 ? 1 : getPageCount();

        return this.lastPage;
    }

    public boolean getPrevAllPageExist() {
        if(getFirstPage() - 1 > 0)
            this.prevAllPageExist = true;
        else
            this.prevAllPageExist = false;

        return this.prevAllPageExist;
    }

    public boolean getNextAllPageExist() {
        if(getLastPage() + 1 > getPageCount())
            this.nextAllPageExist = false;
        else
            this.nextAllPageExist = true;

        return this.nextAllPageExist;
    }

    public boolean getPrevPageExist() {
        if(this.nowPage > 1)
            this.prevPageExist = true;
        else
            this.prevPageExist = false;

        return this.prevPageExist;
    }

    public boolean getNextPageExist() {
        if(this.nowPage == getPageCount() || getPageCount() == 0)
            this.nextPageExist = false;
        else
            this.nextPageExist = true;

        return this.nextPageExist;
    }
}
