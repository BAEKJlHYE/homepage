package anonymous.homepage.board.vo;

import lombok.Data;

@Data
public class BoardVO {
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private int boardOrder;
    private String registDt;
    private int suggestionCount;
    private int viewCount;

    // 추후 BaseVO로 이동 예정
    private String userId;
}
