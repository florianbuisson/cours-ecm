package fr.cmm.helper;

import org.junit.Test;
import static org.junit.Assert.*;

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
}