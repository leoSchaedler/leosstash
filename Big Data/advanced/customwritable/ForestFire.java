package advanced.customwritable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.io.Text;
import org.apache.log4j.BasicConfigurator;
import java.io.IOException;

public class ForestFire {

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        Configuration c = new Configuration();
        String[] files = new GenericOptionsParser(c, args).getRemainingArgs();

        Path input = new Path(files[0]);

        Path output = new Path(files[1]);

        Job j = new Job(c, "forestfire");

        j.setJarByClass(ForestFire.class);
        j.setMapperClass(ForestFireMapper.class);
        j.setReducerClass(ForestFireReducer.class);
        j.setCombinerClass(ForestFireReducer.class);

        j.setMapOutputKeyClass(Text.class);
        j.setOutputValueClass(ForestFireWritable.class);
        j.setOutputKeyClass(Text.class);
        j.setOutputValueClass(ForestFireWritable.class);

        FileInputFormat.addInputPath(j, input);
        FileOutputFormat.setOutputPath(j, output);

        System.exit(j.waitForCompletion(true) ? 0 : 1);
    }

    public static class ForestFireMapper extends Mapper<Object, Text, Text, ForestFireWritable> {
        public void map(Object key, Text value, Context context) throws IOException,
                InterruptedException {

            String campos[] = value.toString().split(",");


            String mes = campos[2];
            double temp = Double.parseDouble(campos[8]);
            double vento = Double.parseDouble(campos[10]);

            context.write(new Text(mes), new ForestFireWritable(temp, vento));

        }
    }

    public static class ForestFireReducer extends Reducer<Text, ForestFireWritable, Text, ForestFireWritable> {

        public void reduce(Text key,
                           Iterable<ForestFireWritable> values,
                           Context context) throws IOException, InterruptedException {

            double maxTemp = Double.MIN_VALUE;
            double maxVento = Double.MIN_VALUE;

            for (ForestFireWritable o : values){
                if (o.getTemp() > maxTemp)
                    maxTemp = o.getTemp();

                if (o.getVento() > maxVento)
                    maxVento = o.getVento();
            }

            context.write(key, new ForestFireWritable(maxTemp, maxVento));
        }
    }
}