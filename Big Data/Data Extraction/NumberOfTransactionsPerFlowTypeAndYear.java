package br.puc.pr.TDE02.atv07;

import br.puc.pr.TDE02.atv06.CommodityWithHighestPricePerUnitTypeAndYear;
import br.puc.pr.TDE02.atv06.CommodityWithHighestPricePerUnitTypeAndYearWritable_Key;
import br.puc.pr.TDE02.atv06.CommodityWithHighestPricePerUnitTypeAndYearWritable_Values;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
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

public class NumberOfTransactionsPerFlowTypeAndYear {

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
        j.setJarByClass(NumberOfTransactionsPerFlowTypeAndYear.class);
        j.setMapperClass(NumberOfTransactionsPerFlowTypeAndYear.TDEMapper.class);
        j.setReducerClass(NumberOfTransactionsPerFlowTypeAndYear.TDEReducer.class);
        j.setCombinerClass(NumberOfTransactionsPerFlowTypeAndYear.TDEReducer.class);

        // tipos de chave e valor de saida
//          map
        j.setMapOutputKeyClass(NumberOfTransactionsPerFlowTypeAndYearWritable.class);
        j.setMapOutputValueClass(LongWritable.class);
//          reduce
        j.setOutputKeyClass(NumberOfTransactionsPerFlowTypeAndYearWritable.class);
        j.setOutputValueClass(LongWritable.class);

        FileInputFormat.addInputPath(j, input);
        FileOutputFormat.setOutputPath(j, output);

        System.exit(j.waitForCompletion(true) ? 0 : 1);
    }

    public static class TDEMapper extends Mapper<Object, Text, NumberOfTransactionsPerFlowTypeAndYearWritable, LongWritable> {
        public void map(Object key, Text value, Context context) throws IOException,
                InterruptedException {

            String campos[] = value.toString().split(";");

            if (!value.toString().contains("country_or_area")) {

                //keys -> NumberOfTransactionsPerFlowTypeAndYearWritable
                String flowType = campos[4];
                String year = campos[1];

                //value
                int counter = 1;

                context.write(new NumberOfTransactionsPerFlowTypeAndYearWritable(flowType, year), new LongWritable(counter));
            }
        }
    }

    public static class TDEReducer extends Reducer<NumberOfTransactionsPerFlowTypeAndYearWritable, LongWritable, NumberOfTransactionsPerFlowTypeAndYearWritable, LongWritable> {

        public void reduce(NumberOfTransactionsPerFlowTypeAndYearWritable key,
                           Iterable<LongWritable> values,
                           Context context) throws IOException, InterruptedException {

            //pegar qual o maior commodity

            long counting = 0;
            for (LongWritable o : values)
                counting += o.get();

            context.write(key, new LongWritable(counting));
        }
    }

}
