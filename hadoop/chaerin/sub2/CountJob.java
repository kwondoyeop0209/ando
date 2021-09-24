package card;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
public class CountJob {
  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    // ����� ������ ��� Ȯ��
    if (args.length != 2) {
      System.err.println("Usage: CountJob <input> <output>");
      System.exit(2);
    }
    // Job �̸� ����
    Job job = Job.getInstance(conf, "CountJob");
    // ����� ������ ��� ����
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    // Job Ŭ���� ����
    job.setJarByClass(CountJob.class);
    // Mapper Ŭ���� ����
    job.setMapperClass(CountMapper.class);
    // Reducer Ŭ���� ����
    job.setReducerClass(CountReducer.class);
    // ����� ������ ���� ����
    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);
    // ���Ű �� ��°� ���� ����
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    job.waitForCompletion(true);
  }
}