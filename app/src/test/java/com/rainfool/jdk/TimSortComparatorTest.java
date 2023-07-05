package com.rainfool.jdk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TimSortComparatorTest {

    @Test
    void testComparator() {
        Message lhs = new Message();
        Message rhs = new Message();
        lhs.sequence = 1;
        rhs.sequence = 2;
        TimSortComparator comparator = new TimSortComparator();
        assertEquals(-1, comparator.compare(lhs, rhs));
    }
}