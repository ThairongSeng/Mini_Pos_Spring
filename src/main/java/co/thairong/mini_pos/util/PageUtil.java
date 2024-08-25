package co.thairong.mini_pos.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtil {

    int DEFAULT_PAGE_LIMIT = 20;
    int DEFAULT_PAGE_NUMBER = 1;

    String PAGE_LIMIT = "_limit";
    String PAGE_NUM = "_page";
    String SEARCH_NAME = "_name";

    static Pageable getPageable(int pageNum, int pageSize) {
       if (pageNum < 1 || pageSize < 1) {
           pageNum = DEFAULT_PAGE_NUMBER;
           pageSize = DEFAULT_PAGE_LIMIT;
       }

        PageRequest pageable = PageRequest.of(pageNum - 1, pageSize);

       return pageable;

    }
}
