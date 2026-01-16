package com.eirzarog.bookstore_management.entity.mappers;

import com.eirzarog.bookstore_management.entity.dtos.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaginationMapper {

    public <T> PageResponseDTO<T> toPageResponse(
            Page<T> page,
            int pageNumber,
            int size,
            Map<String, Object> filters,
            String basePath
    ) {

        // Build query string from filters
        String queryString = buildQueryString(filters);

        // Navigation links
        Map<String, String> links = new HashMap<>();
        links.put("self", basePath + "?page=" + pageNumber + "&size=" + size + queryString);

        links.put("next", page.isLast()
                ? null
                : basePath + "?page=" + (pageNumber + 1) + "&size=" + size + queryString);

        links.put("prev", page.isFirst()
                ? null
                : basePath + "?page=" + (pageNumber - 1) + "&size=" + size + queryString);

        // UI metadata
        boolean hasNext = !page.isLast();
        boolean hasPrev = !page.isFirst();

        long rangeStart = (long) pageNumber * size + 1;
        long rangeEnd = rangeStart + page.getNumberOfElements() - 1;

        return new PageResponseDTO<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.getSort().isSorted(),
                hasNext,
                hasPrev,
                rangeStart,
                rangeEnd,
                links,
                filters
        );
    }

    private String buildQueryString(Map<String, Object> filters) {
        StringBuilder sb = new StringBuilder();
        filters.forEach((key, value) -> {
            if (value != null) {
                sb.append("&").append(key).append("=").append(value);
            }
        });
        return sb.toString();
    }
}
