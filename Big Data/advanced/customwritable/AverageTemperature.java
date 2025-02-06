package advanced.customwritable;

import basic.Teste;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class AverageTemperature {

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        BasicConfigurator.configure();

        Configuration c = new Configuration();
        String[] files = new GenericOptionsParser(c, args).getRemainingArgs();
        Path input = new Path(files[0]);

        Path output = new Path(files[1]);

        Job j = new Job(c, "media");

        j.setJarByClass(AverageTemperature.class);
        j.setMapperClass(MapForAverage.class);
        j.setReducerClass(ReduceForAverage.class);
        j.setCombinerClass(CombinerForAverage.class);

        j.setMapOutputKeyClass(Text.class);
        j.setMapOutputValueClass(FireAvgTempWritable.class);
        j.setOutputKeyClass(Text.class);
        j.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(j, input);
        FileOutputFormat.setOutputPath(j, output);
        System.exit(j.waitForCompletion(true) ? 0 : 1);

    }


    public static class MapForAverage extends Mapper<LongWritable, Text, Text, FireAvgTempWritable> {

        public void map(LongWritable key, Text value, Context con)
                throws IOException, InterruptedException {


            String linha = value.toString();

            String[] colunas = linha.split(";");

            double temp = Double.parseDouble(colunas[5]);

            String mes = colunas[1];

            long n = 1;

            con.write(new Text(mes), new FireAvgTempWritable(n, temp));
            con.write(new Text("media de tudo"), new FireAvgTempWritable(n, temp));


        }
    }

    public static class ReduceForAverage extends Reducer<Text, FireAvgTempWritable, Text, DoubleWritable> {
        public void reduce(Text key, Iterable<FireAvgTempWritable> values, Context con)
                throws IOException, InterruptedException {

            double somaTemp = 0.0;
            long somaNs = 0;
            for (FireAvgTempWritable o : values){
                somaTemp += o.getSomaTemp();
                somaNs += o.getOcorrencia();
            }

            con.write(new Text(key), new DoubleWritable(somaTemp / somaNs));
        }
    }

    public static class CombinerForAverage extends Reducer<Text, FireAvgTempWritable, Text, FireAvgTempWritable>{

        public void reduce (Text key, Iterable<FireAvgTempWritable> values, Context con)
                throws IOException, InterruptedException {

            double somaTemp = 0.0;
            long somaNs = 0;
            for (FireAvgTempWritable o : values){
                somaTemp += o.getSomaTemp();
                somaNs += o.getOcorrencia();
            }

            con.write(key, new FireAvgTempWritable(somaNs, somaTemp));

        }

    }

}
