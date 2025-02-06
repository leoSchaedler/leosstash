package br.puc.pr.TDE02.atv04;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class AverageOfCommodityValuesPerYear {

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        Configuration c = new Configuration();
        String[] files = new GenericOptionsParser(c, args).getRemainingArgs();

        // arquivo de entrada
        Path input = new Path(files[0]);
//        Path inputB = new Path(files[1]);

        // arquivo de saida
        Path output = new Path(files[1]);

        // criacao do job e seu nome
        Job j = new Job(c, "transactions");

        // registro de classes
        j.setJarByClass(AverageOfCommodityValuesPerYear.class);
        j.setMapperClass(AverageOfCommodityValuesPerYear.TDEMapper.class);
        j.setReducerClass(AverageOfCommodityValuesPerYear.TDEReducer.class);
        j.setCombinerClass(AverageOfCommodityValuesPerYear.TDECombiner.class);

        // tipos de chave e valor de saida
//          map
        j.setMapOutputKeyClass(Text.class);
        j.setMapOutputValueClass(AverageOfCommodityValuesPerYearWriteble.class);
//          reduce
        j.setOutputKeyClass(Text.class);
        j.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(j, input);
        FileOutputFormat.setOutputPath(j, output);

        System.exit(j.waitForCompletion(true) ? 0 : 1);
    }

    public static class TDEMapper extends Mapper<LongWritable, Text, Text, AverageOfCommodityValuesPerYearWriteble> {
        public void map(LongWritable key, Text value, Context context) throws IOException,
                InterruptedException {

            String campos[] = value.toString().split(";");

            if (!value.toString().contains("country_or_area")) {

                double price = Double.parseDouble(campos[5]);

                String year = campos[1];

                long count = 1;

                context.write(new Text(year), new AverageOfCommodityValuesPerYearWriteble(price, count));
            }
        }
    }

    public static class TDEReducer extends Reducer<Text, AverageOfCommodityValuesPerYearWriteble, Text, DoubleWritable> {

        public void reduce(Text key,
                           Iterable<AverageOfCommodityValuesPerYearWriteble> values,
                           Context context) throws IOException, InterruptedException {

            double summingPrice = 0;
            long summingCount = 0;
            for (AverageOfCommodityValuesPerYearWriteble o : values) {
                summingPrice += o.getPrice();
                summingCount += o.getCount();
            }



            context.write(key, new DoubleWritable(summingPrice/summingCount));
        }
    }

    public static class TDECombiner extends Reducer<Text, AverageOfCommodityValuesPerYearWriteble, Text, AverageOfCommodityValuesPerYearWriteble> {

        public void reducer(Text key,
                           Iterable<AverageOfCommodityValuesPerYearWriteble> values,
                           Context context) throws IOException, InterruptedException {

            double summingPrice = 0;
            long summingCount = 0;
            for (AverageOfCommodityValuesPerYearWriteble o : values) {
                summingPrice += o.getPrice();
                summingCount += o.getCount();
            }

            context.write(key, new AverageOfCommodityValuesPerYearWriteble(summingPrice, summingCount));
        }
    }
}
