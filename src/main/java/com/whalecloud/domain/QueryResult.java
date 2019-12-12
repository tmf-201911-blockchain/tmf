package com.whalecloud.domain;


import java.util.List;

public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
    //页数
    private int pages;

    //总租金
    private Object totalActualRate;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Object getTotalActualRate() {
        return totalActualRate;
    }

    public void setTotalActualRate(Object totalActualRate) {
        this.totalActualRate = totalActualRate;
    }
}
