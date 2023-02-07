package anonymous.homepage.file.vo;

import lombok.Data;

@Data
public class AtchFileVO {
    private String atchFileId;
    private String atchDocId;
    private String fileNm;
    private String orginlFileNm;
    private long fileSize;
    private String registId;
    private String registDt;
    private String updateId;
    private String updateDt;
    private String useYn;
}
