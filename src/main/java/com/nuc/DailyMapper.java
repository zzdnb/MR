package com.nuc;

import com.entity.Daily;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author 福尔摩东
 * @date 2022/5/7 16:33
 * @公众号 IT云家
 * @Github https://github.com/zzdnb
 * @博客 https://blog.csdn.net/qq_43688587
 * @网站 https://blog.zzdnb.cn
 */
public class DailyMapper  extends Mapper<LongWritable, Text, Text, Daily> {
    private Text outK = new Text();
    private Daily outV = new Daily();
    @Override
    protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {

        String line=new String(value.getBytes(),0,value.getLength(),"GBK");
        String[] fields= line.split(",");
        if (fields.length<8){
            return;
        }else {
            String id=fields[0];
            String studentNo = fields[1];
            //班级编号
            String classNo = fields[2];
            //所属日期
            String time = fields[3];
            //座右铭
            String motto = fields[4];
            //工作内容
            String workContent = fields[5];
            //完成率
            String completion = fields[6];
            // 备注
            String note = fields[7];
            //日报添加时间
            String addTime = fields[8];
            //日报/周报 0/1
            String isDaily = fields[9];

            outV.setId(id);
            outV.setStudentNo(studentNo);
            outV.setClassNo(classNo);
            outV.setTime(time);
            outV.setMotto(motto);
            outV.setWorkContent(workContent);
            outV.setCompletion(completion);
            outV.setNote(note);
            outV.setAddTime(addTime);
            outV.setIsDaily(isDaily);


            outK.set("");
            context.write(outK,outV);
        }


    }
}
