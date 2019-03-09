package com.xshwd.framework.web;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Auther: SK ON  2018/11/2 16:00
 * @Description:
 */
public class Pagination {
    private Integer totalRow;  // 数据量
    private Integer pageSize;  // 每页最多显示多少条数据
    private Integer pageIndex; // 当前页码
    private Integer pageCount; // 总页数

    public Pagination(){}

    public Pagination(Integer totalRow, Integer pageSize, Integer pageIndex) {
        this.totalRow = totalRow;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        if (!(0 == (null == totalRow ? 0 : totalRow)) && !(0 == (null == pageSize ? 0 : pageSize))) {
            this.pageCount = (int) (Math.ceil(1.0 * totalRow / pageSize));
        }
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
