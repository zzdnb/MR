package com.nuc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 福尔摩东
 * @date 2022/5/7 16:25
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */
public class MRDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {

        //1 获取job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //2 关联Driver类
        job.setJarByClass(MRDriver.class);
        int sz = args[1].split("/").length;
        if ("daily".equals(args[1].split("/")[sz - 1])) {
            //3 关联Mapper和Reducer类
            job.setMapperClass(DailyMapper.class);
            job.setReducerClass(DailyReducer.class);

            //4 设置Map的输入输出类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Daily.class);

            //5	设置最终的输入输出类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Daily.class);
        } else {
            //3 关联Mapper和Reducer类
            job.setMapperClass(ExamMapper.class);
            job.setReducerClass(ExamReducer.class);

            //4 设置Map的输入输出类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Exam.class);

            //5	设置最终的输入输出类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(Exam.class);
        }
        //6	设置输入输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileSystem fs = FileSystem.get(new URI("hdfs://180.76.243.209:9000"), conf, "root");

        Path pathout = new Path(args[1]);
        if (fs.exists(pathout)) {
            fs.delete(pathout, true);
        }
        FileOutputFormat.setOutputPath(job, pathout);
        //7 提交job
        boolean result = job.waitForCompletion(true);
        if (result) {
            System.out.println("输出成功 -......666");
        }
    }
}
