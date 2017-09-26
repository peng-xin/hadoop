package com.px.secondarySort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class CompositePartitioner extends Partitioner<CompositeKey,IntWritable> {

    @Override
    public int getPartition(CompositeKey compositeKey, IntWritable intWritable, int i) {
        System.out.println("分发器启动");
        return Math.abs(compositeKey.getDate().hashCode()%i);
    }
}
