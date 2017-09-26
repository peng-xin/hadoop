package com.px.secondarySort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class Reduce extends Reducer<CompositeKey,IntWritable,Text,IntWritable> {

    private Text text=new Text();

    public void close() throws IOException {

    }

    public void configure(JobConf jobConf) {

    }

    @Override
    protected void reduce(CompositeKey compositeKey, Iterable<IntWritable> iterator, Context context) throws IOException, InterruptedException {
        System.out.println("归约器启动");
        if (compositeKey != null) {
            text.set(compositeKey.getDate());
            iterator.forEach((IntWritable value) -> {
                try {
                    System.out.println("归约器："+text+"->"+value);
                    context.write(text, value);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("归约器结束");
    }
}
