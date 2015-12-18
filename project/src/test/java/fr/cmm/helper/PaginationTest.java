package fr.cmm.helper;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class PaginationTest {
    @Test
    public void testCountPaginationMultiple() {
        Pagination pagination = new Pagination();
        pagination.setCount(25);
        pagination.setPageSize(5);

        assertEquals(pagination.getPageCount(), 5);
    }

    @Test
    public void testCountPaginationNotMultiple() {
        Pagination pagination = new Pagination();
        pagination.setCount(26);
        pagination.setPageSize(5);

        assertEquals(pagination.getPageCount(), 6);
    }

    @Test
    public void testCountPaginationZero() {
        Pagination pagination = new Pagination();
        pagination.setCount(0);
        pagination.setPageSize(5);

        assertEquals(pagination.getPageCount(), 1);
    }

    @Test
    public void testGetPages() {
        Pagination pagination2 = new Pagination();
        pagination2.setPageSize(1);
        pagination2.setCount(4);
        pagination2.setPageIndex(1);
        assertEquals(pagination2.getPages(), asList(1,2,3,4));

    }
}