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
        Pagination pagination = new Pagination();
        pagination.setPageSize(5);
        pagination.setCount(55);
        assertEquals(pagination.getPages(), asList(1,2,3,4,5,6,7,8,9,10));
    }
}