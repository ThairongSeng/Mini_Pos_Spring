package co.thairong.mini_pos.model.dto;


import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDto {
    private List<?> listData;
    private PaginationDto pagination;

    public PageDto(Page<?> page) {
        this.listData = page.getContent();
        this.pagination = PaginationDto.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getPageable().getPageSize())
                .pageNumber(page.getPageable().getPageNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .build();
    }
}
