package com.px.secondarySort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class Map extends Mapper<LongWritable,Text,CompositeKey,IntWritable> {
    private CompositeKey compositeKey=new CompositeKey();
    private IntWritable intWritable=new IntWritable();

    @Test
    public void test(){
        System.out.println(Arrays.toString(new String("2017.08\t14791 \n" +
                "2017.02\t10864 \n" +
                "\n" +
                "2017.04\t13834 \n" +
                "\n" +
                "2017.01\t37202").split("\t")));
        System.out.println(new String("2017.08\t14791 \n" +
                "\n").split("\\t")[0].contains("."));
        System.out.println(DateTime.parse(new String("2017.08\t14791 \n").split("\\t")[0], DateTimeFormat.forPattern("YYYY.MM")).compareTo(DateTime.parse("2012.06", DateTimeFormat.forPattern("YYYY.MM"))));
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("映射器启动");
        String line=value.toString();
        if (line == null || line.equals("")) {
            return;
        }
        String[] words=line.split("\\t");
        if(words.length>1){
            if (words[0].contains(".")){
                compositeKey.setDate(words[0]);
                try {
                    compositeKey.setAfre(Integer.valueOf(words[1].trim()));
                }catch (Exception e){
                    return;
                }
                intWritable.set(Integer.valueOf(words[1].trim()));
                context.write(compositeKey,intWritable);
            }else {
                System.out.println("日期切分出错"+key.get());
                return;
            }
        }else {
            System.out.println("此行数据格式不对"+key.get());
        }
        System.out.println("映射器结束");
    }
}
