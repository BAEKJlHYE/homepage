package anonymous.homepage.vo;

import lombok.Data;

@Data
public class BaseVO {
    // 사용자 정보
    String userId = "admin";    // 사용자 ID (로그인 시 저장하는 것으로 수정 필요)
    boolean isPermitted;        // 권한 존재 여부

    public boolean getIsPermitted() {
        if(this.userId == "admin")
            this.isPermitted = true;
        else
            this.isPermitted = false;

        return this.isPermitted;
    }

    // 처리 결과
    String resultMessage;

    // 검색
    String searchSe;            // 검색 구분
    String searchKeyword;       // 검색어

    // 페이징
    int recordCount;            // 게시글 개수
    int recordUnit;             // 한 페이지 당 게시할 게시글 개수
    int firstRecordNumber;      // 시작 rownum
    int lastRecordNumber;       // 종료 rownum
    int nowPage;                // 현재 페이지
    int pageUnit;               // 페이지에 게시할 페이지 개수
    int pageCount;              // 총 페이지 개수
    int firstPage;              // 현재 페이지의 첫 번째 페이지
    int lastPage;               // 현재 페이지의 마지막 페이지
    boolean prevAllPageExist;   // '<<' 표시 여부
    boolean nextAllPageExist;   // '>>' 표시 여부
    boolean prevPageExist;      // '<' 표시 여부
    boolean nextPageExist;      // '>' 표시 여부

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
            this.lastPage = getPageCount();

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
        if(this.nowPage == getPageCount())
            this.nextPageExist = false;
        else
            this.nextPageExist = true;

        return this.nextPageExist;
    }
}
