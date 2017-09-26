package com.px.secondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class CompositeComparator extends WritableComparator {
    public CompositeComparator() {
        super(CompositeKey.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        System.out.println("分法排序");
        return DateTime.parse(((CompositeKey)a).getDate(), DateTimeFormat.forPattern("YYYY.MM")).compareTo(DateTime.parse(((CompositeKey)b).getDate(), DateTimeFormat.forPattern("YYYY.MM")));
    }
}
