package com.rainfool.jdk;

import java.util.Comparator;

public class TimSortComparator implements Comparator<Message> {
    @Override
    public int compare(Message lhs, Message rhs) {
        if (lhs.sequence > 0 && rhs.sequence > 0) {
            return Long.compare(lhs.sequence, rhs.sequence);
        }
        if (lhs.time == null && rhs.time == null) {
            return 0;
        }
        if (lhs.time == null) {
            return -1;
        }
        if (rhs.time == null) {
            return 1;
        }
        return lhs.time.compareTo(rhs.time);
    }
}
