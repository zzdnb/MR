package com.nuc;

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
public class ExamMapper extends Mapper<LongWritable, Text, Text, Exam> {
    private Text outK = new Text();
    private Exam outV = new Exam();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = new String(value.getBytes(), 0, value.getLength(), "GBK");
        String[] fields = line.split(",");
        //试卷id
        String paperNo = fields[1];
        //学生id
        String studentNo = fields[2];
        //主观题得分
        String subjectiveScore = fields[3];
        //客观题得分
        String objectiveScore = fields[4];
        //总得分
        String score = fields[5];
        //老师是否批阅 1/2
        String isRead = fields[6];
        //是否正常提交
        String isSubmit = fields[7];
        //是否正常进入考试  1/2
        String isEnter = fields[8];
        //客观题各个题型对应的得分   1单选 2多选 3判断
        String subjectiveDetails = fields[9];
        //主观题各个题型对应的得分  4 5 6 7分别代表主观题的题型
        String objectiveDetails = fields[10];


        outV.setPaperNo(paperNo);
        outV.setStudentNo(studentNo);
        outV.setSubjectiveScore(subjectiveScore);
        outV.setObjectiveScore(objectiveScore);
        outV.setScore(score);
        outV.setIsRead(isRead);
        outV.setIsSubmit(isSubmit);
        outV.setIsEnter(isEnter);
        outV.setSubjectiveDetails(subjectiveDetails);
        outV.setObjectiveDetails(objectiveDetails);

        outK.set("");
        context.write(outK,outV);
    }
}
