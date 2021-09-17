package ssafy;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class barCountByHJD {
	
	public static class barMapper
			extends Mapper<Object,Text,Text,IntWritable> {

		private Text word = new Text();

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {

			//,
        		String[] strs = value.toString().split(",");
        		word.set(strs[5] + " " + strs[9]);
        		IntWritable one = new IntWritable(1);

        		context.write(word, one);
		}
	}

	
	public static class barReducer
			extends Reducer<Text,IntWritable,Text,IntWritable> {

		private IntWritable result = new IntWritable();

	    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {

        int sum = 0;
        for(IntWritable value : values)
            sum += value.get();
        result.set(sum);

        context.write(key, result);
    }
	}
	
	public static class MyPartitioner extends Partitioner <Text, IntWritable> {
		@Override
		public int getPartition(Text key, IntWritable value, int numPartitions) {
			if (key.toString().charAt(0) < 'a') return 0;
			else return 1;
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
		if ( otherArgs.length != 2 ) {
			System.err.println("Usage: <in> <out>");
			System.exit(2);
		}
		FileSystem hdfs = FileSystem.get(conf);
    		Path output = new Path(otherArgs[1]);
    		if (hdfs.exists(output))
            		hdfs.delete(output, true);
            
		Job job = new Job(conf,"bar");
		job.setJarByClass(barCountByHJD.class);

		// let hadoop know my map and reduce classes
		job.setMapperClass(barMapper.class);
		job.setCombinerClass(barReducer.class);
		job.setReducerClass(barReducer.class);

		job.setOutputKeyClass(Text.class);
        	job.setOutputValueClass(IntWritable.class);
		job.setPartitionerClass(MyPartitioner.class);

		// set number of reduces
		job.setNumReduceTasks(1);

		// set input and output directories
		FileInputFormat.addInputPath(job,new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1 );
	}
}

