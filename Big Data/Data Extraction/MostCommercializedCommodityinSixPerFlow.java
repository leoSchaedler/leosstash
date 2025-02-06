package br.puc.pr.TDE02.atv03;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class MostCommercializedCommodityinSixPerFlow {

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
        j.setJarByClass(MostCommercializedCommodityinSixPerFlow.class);
        j.setMapperClass(MostCommercializedCommodityinSixPerFlow.TDEMapper.class);
        j.setReducerClass(MostCommercializedCommodityinSixPerFlow.TDEReducer.class);
        j.setCombinerClass(MostCommercializedCommodityinSixPerFlow.TDECombiner.class);

        // tipos de chave e valor de saida
//        map
        j.setMapOutputKeyClass(Text.class);
        j.setMapOutputValueClass(MostCommercializedCommodityinSixPerFlowWriteble.class);
//          reduce
        j.setOutputKeyClass(Text.class);
        j.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(j, input);
        FileOutputFormat.setOutputPath(j, output);

        System.exit(j.waitForCompletion(true) ? 0 : 1);
    }

    public static class TDEMapper extends Mapper<Object, Text, Text, MostCommercializedCommodityinSixPerFlowWriteble> {
        public void map(Object key, Text value, Context context) throws IOException,
                InterruptedException {

            if (!value.toString().contains("country_or_area")) {

                String campos[] = value.toString().split(";");

                String year = campos[1];

                if (year.equals("2016")) {

                    String flow = campos[4];

                    String commodity = campos[3].split(",")[0];

                    long count = 1;

                    context.write(new Text(flow), new MostCommercializedCommodityinSixPerFlowWriteble(count, commodity));
                }
            }
        }
    }

    public static class TDECombiner extends Reducer<Text, MostCommercializedCommodityinSixPerFlowWriteble, Text, MostCommercializedCommodityinSixPerFlowWriteble> {

        public void combiner(Text key,
                            Iterable<MostCommercializedCommodityinSixPerFlowWriteble> values,
                            Context context) throws IOException, InterruptedException {

            long summingCount = 0;
            for (MostCommercializedCommodityinSixPerFlowWriteble o : values) {
                summingCount += o.getCont();
                context.write(key, new MostCommercializedCommodityinSixPerFlowWriteble(summingCount, o.getCommodity()));
            }
        }
    }

    public static class TDEReducer extends Reducer<Text, MostCommercializedCommodityinSixPerFlowWriteble, Text, Text> {

        public void reduce(Text key,
                           Iterable<MostCommercializedCommodityinSixPerFlowWriteble> values,
                           Context context) throws IOException, InterruptedException {

            String mostComercializedCommodity = "";
            long mostCommercialized = Long.MIN_VALUE;

            for (MostCommercializedCommodityinSixPerFlowWriteble o : values)
                if (o.getCont() > mostCommercialized){
                    mostCommercialized = o.getCont();
                    mostComercializedCommodity = o.getCommodity();
                }


            context.write(key, new Text(mostComercializedCommodity));
        }
    }


}
