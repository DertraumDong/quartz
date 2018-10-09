package com.traum.quartz.entity;

/**
 * Entity基类
 */
public class BaseEntity {

    /**
     * pageNum
     */
    private int pageNumKey;

    /**
     * pageSize
     */
    private int pageSizeKey;

    public int getPageNumKey() {
        return pageNumKey;
    }

    public void setPageNumKey(int pageNumKey) {
        this.pageNumKey = pageNumKey;
    }

    public int getPageSizeKey() {
        return pageSizeKey;
    }

    public void setPageSizeKey(int pageSizeKey) {
        this.pageSizeKey = pageSizeKey;
    }
}
