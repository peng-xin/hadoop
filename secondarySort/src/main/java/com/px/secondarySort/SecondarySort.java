package com.px.secondarySort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

public class SecondarySort extends Configuration implements Tool {

    public int run(String[] strings) throws Exception {
        System.out.println("驱动器启动");
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration, "SecondarySort");
        job.setJarByClass(SecondarySort.class);
        job.setJobName("二次排序");

        FileInputFormat.setInputPaths(job,new Path(strings[0]));
        FileOutputFormat.setOutputPath(job,new Path(strings[1]));

        job.setMapperClass(Map.class);
        job.setMapOutputKeyClass(CompositeKey.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setGroupingComparatorClass(CompositeComparator.class);
        job.setPartitionerClass(CompositePartitioner.class);

        job.setReducerClass(Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        boolean status=job.waitForCompletion(true);
        Logger.getRootLogger().info("Dirver:"+status);
        System.out.println("驱动器结束");
        return status?1:0;
    }

    public void setConf(Configuration configuration) {

    }

    public Configuration getConf() {
        return null;
    }

    public static void main(String[] args) throws Exception {
        int exitcode= ToolRunner.run(new SecondarySort(),args);
        System.exit(exitcode);
    }
}
