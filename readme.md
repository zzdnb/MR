## 本项目用于处理CSV数据中内容的空行与回车，处理完的数据上传到服务器，可供Hive仓库直接使用

> 数据处理步骤：

1. 通过文件上传系统上传到根目录下的input下，并且将文件下载到云服务器上：
`hadoop fs -copyToLocal 原地址 目标地址`
2. 调用脚本对CSV数据进行数据清洗
`python3 changeExam.py`
`python3 changeDaily.py`
3. 将清洗后的数据上传到根目录下的input下，调用MapReduce的程序进行数据的清洗：
`hadoop  jar /opt/software/mr-project-1.0-SNAPSHOT.jar com.nuc.MRDriver /opt/moudle/hadoop/input/学生日报数据-处理.csv /opt/moudle/hadoop/output/daily`
我写的这个程序是可以根据第二个参数进行设置的，具体代码可看MRDriver。此程序会将数据放到根目录下的input目录下

>在hdfs 中查看输出文件出现乱码，解决方法：
第二步以后，执行这个即可
```shell
查看文件编码格式
 ls  | xargs file
设置编码为GBK
:set fileencoding=gbk 
```
