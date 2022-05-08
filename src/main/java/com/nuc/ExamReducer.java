package com.nuc;


import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author 福尔摩东
 * @date 2022/5/7 16:34
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */
public class ExamReducer extends Reducer<Text, Exam, Text, Exam> {
    @Override
    protected void reduce(Text key, Iterable<Exam> values, Context context) throws IOException, InterruptedException {

        for (Exam value : values) {
            context.write(key,value);
        }
    }
}
