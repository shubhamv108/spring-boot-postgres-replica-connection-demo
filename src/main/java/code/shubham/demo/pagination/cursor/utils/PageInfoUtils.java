package code.shubham.demo.pagination.cursor.utils;

import code.shubham.demo.pagination.cursor.utils.pageinfo.PageInfo;
import code.shubham.demo.pagination.cursor.dao.entities.base.PageableEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PageInfoUtils<T extends PageableEntity> {

    public PageInfo getPageInfo(int size, List<T> list, T last) {
        return PageInfo.builder()
                .pageSize(size)
                .previousCursor(formulatePreviousCursor(list))
                .nextCursor(formulateNextCursor(list))
                .lastCursor(CursorUtil.encode(Optional.ofNullable(last).map(p -> p.getCursorValue()).orElse("")))
                .build();
    }

    public String formulatePreviousCursor(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }

        // Get the first item's createdAt and id
        PageableEntity entity = list.get(0);
        String cursorValue = entity.getCursorValue();

        // Encode the cursor
        return CursorUtil.encode(cursorValue);
    }

    public String formulateNextCursor(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }

        // Get the last item's createdAt and id
        PageableEntity entity = list.get(list.size() - 1);
        String cursorValue = entity.getCursorValue();

        // Encode the cursor
        return CursorUtil.encode(cursorValue);
    }
}
