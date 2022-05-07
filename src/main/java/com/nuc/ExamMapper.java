package com.nuc;

import com.entity.Daily;
import com.entity.Exam;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author 福尔摩东
 * @date 2022/5/7 16:34
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */
public class ExamMapper extends Mapper<LongWritable,Text, Text, Exam> {
    private Text outK = new Text();
    private Exam outV = new Exam();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

    }
}
