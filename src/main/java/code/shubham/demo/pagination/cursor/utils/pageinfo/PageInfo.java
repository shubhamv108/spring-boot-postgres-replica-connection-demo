package code.shubham.demo.pagination.cursor.utils.pageinfo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class PageInfo {
    private int pageSize;
    private String previousCursor;
    private String nextCursor;
    private String lastCursor;
}
