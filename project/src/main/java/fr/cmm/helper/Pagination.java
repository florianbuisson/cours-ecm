package fr.cmm.helper;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    // 1 based page index
    private int pageIndex;

    private int pageSize;

    private long count;

    public static final int PAGINATION_SIZE = 10;

    public int getPreviousPageIndex() {
        return isFirstPage() ? pageIndex : pageIndex - 1;
    }

    public int getNextPageIndex() {
        return isLastPage() ? pageIndex : pageIndex + 1;
    }

    public boolean isFirstPage() {
        return pageIndex == 1;
    }

    public boolean isLastPage() {
        return pageIndex * pageSize >= count;
    }

    public int getPageCount() {
        float a = count / pageSize;
        if(count % pageSize == 0 ) {
            if(a==0) {
                return (int) (a+1);
            } else {
                return (int) a;
            }
        } else {
            return (int) a + 1;
        }
    }

    public List<Integer> getPages() {
        List<Integer> listPages = new ArrayList<>();
        for(int i=1; i<= Math.min(getPageCount(),PAGINATION_SIZE); i ++) {
            listPages.add(i);
        }
        return listPages;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
