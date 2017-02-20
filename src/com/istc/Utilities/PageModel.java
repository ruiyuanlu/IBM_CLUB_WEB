package com.istc.Utilities;

/**
 * Created by lurui on 2017/2/3 0003.
 */

import java.util.List;

/**
 * 分页模型类, 前端分页的M层映射结构
 * 在BaseDAOImpl.java中设计了数据库的查询
 */
public class PageModel<E> {

    private int pageNum;
    private int pageSize;
    private int allRecordCount;
    private List<E> datas;

    public PageModel() {
    }

    public PageModel(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getAllRecordCount() {
        return allRecordCount;
    }

    public void setAllRecordCount(int allRecordCount) {
        this.allRecordCount = allRecordCount;
    }

    public List<E> getDatas() {
        return datas;
    }

    public void setDatas(List<E> datas) {
        this.datas = datas;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
