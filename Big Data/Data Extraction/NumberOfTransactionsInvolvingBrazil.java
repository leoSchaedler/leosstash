package br.puc.pr.TDE02.atv01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class NumberOfTransactionsInvolvingBrazil {

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
        j.setJarByClass(NumberOfTransactionsInvolvingBrazil.class);
        j.setMapperClass(NumberOfTransactionsInvolvingBrazil.TDEMapper.class);
        j.setReducerClass(NumberOfTransactionsInvolvingBrazil.TDEReducer.class);
        j.setCombinerClass(NumberOfTransactionsInvolvingBrazil.TDEReducer.class);    //combiner

        // tipos de chave e valor de saida
//        map
        j.setMapOutputKeyClass(Text.class);
        j.setMapOutputValueClass(IntWritable.class);
//          reduce
        j.setOutputKeyClass(Text.class);
        j.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(j, input);
        FileOutputFormat.setOutputPath(j, output);

        System.exit(j.waitForCompletion(true) ? 0 : 1);
    }

    public static class TDEMapper extends Mapper<Object, Text, Text, IntWritable> {
        public void map(Object key, Text value, Context context) throws IOException,
                InterruptedException {

            if (!value.toString().contains("country_or_area")){

                String campos[] = value.toString().split(";");

                String country = campos[0];

                IntWritable cont = new IntWritable(1);

                if (country.equals("Brazil"))
                    context.write(new Text(country), cont);
            }
        }
    }

    public static class TDEReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        public void reduce(Text key,
                           Iterable<IntWritable> values,
                           Context context) throws IOException, InterruptedException {

            int cont = 0;

            for (IntWritable o : values)
                cont += o.get();

            IntWritable valorSaida = new IntWritable(cont);

            context.write(key, valorSaida);
        }
    }

}
