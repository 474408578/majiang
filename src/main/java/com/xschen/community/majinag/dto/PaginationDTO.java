package com.xschen.community.majinag.dto;

import com.xschen.community.majinag.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirst;
    private boolean showNext;
    private boolean showEnd;
    private Integer currentPage;
    // pages前三页和后三页
    private List<Integer> pages = new ArrayList<>();
    // 总页数
    private Integer totalPage;

    /**
     * @param totalCount 总行数
     * @param page 第几页
     * @param size 每页多少条
     */
    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.currentPage = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            // 向前展示三个
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            // 向后展示三个
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 是否显示上一页
        showPrevious = (page == 1) ? false : true;

        // 是否显示下一页
        showNext = (page == totalPage) ? false : true;

        // 是否显示第一页
        showFirst = pages.contains(1) ? false : true;

        // 是否显示最后一页
        showEnd = pages.contains(totalPage) ? false : true;
    }
}
