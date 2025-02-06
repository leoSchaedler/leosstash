package br.puc.pr.TDE02.atv05;

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

public class AveragePriceOfCommoditiesPerUnitInBrazilExportation {

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
        Job j = new Job(c, "forestfire");

        // registro de classes
        j.setJarByClass(AveragePriceOfCommoditiesPerUnitInBrazilExportation.class);
        j.setMapperClass(AveragePriceOfCommoditiesPerUnitInBrazilExportation.TDEMapper.class);
        j.setReducerClass(AveragePriceOfCommoditiesPerUnitInBrazilExportation.TDEReducer.class);
        j.setCombinerClass(AveragePriceOfCommoditiesPerUnitInBrazilExportation.TDECombiner.class);

        // tipos de chave e valor de saida
//          map
        j.setMapOutputKeyClass(AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key.class);
        j.setMapOutputValueClass(AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values.class);
//          reduce
        j.setOutputKeyClass(AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key.class);
        j.setOutputValueClass(LongWritable.class);

        FileInputFormat.addInputPath(j, input);
        FileOutputFormat.setOutputPath(j, output);

        System.exit(j.waitForCompletion(true) ? 0 : 1);
    }

    public static class TDEMapper extends Mapper<Object, Text, AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key, AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values> {
        public void map(Object key, Text value, Context context) throws IOException,
                InterruptedException {

            String campos[] = value.toString().split(";");

            if (!value.toString().contains("country_or_area")) {

                String contry = campos[0];
                String flow = campos[4];

                if (contry.equals("Brazil") && flow.equals("Export")) {

                    //key -> AveragePriceOfCommoditiesPerUnitInBrazilExportationWriteble_Key
                    String unitType = campos[7];
                    String year = campos[1];
                    String category = campos[9];

                    //values -> AveragePriceOfCommoditiesPerUnitInBrazilExportationWriteble_Values
                    double price = Double.parseDouble(campos[5]);
                    String commodity = campos[3].split(",")[0];
                    long cont = 1;

                    context.write(new AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key(unitType, year, category), new AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values(price, commodity, cont));
                }
            }
        }
    }

    public static class TDECombiner extends Reducer<AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key, AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values, AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key, AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values> {

        public void combiner(AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key key,
                             Iterable<AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values> values,
                             Context context) throws IOException, InterruptedException {

            long summingCount = 0;
            double summingPrice = 0;
            for (AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values o : values) {
                summingCount += o.getCont();
                summingPrice += o.getPrice();
                context.write(key, new AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values(summingPrice, summingCount, o.getCommodity()));
            }

        }
    }

    public static class TDEReducer extends Reducer<AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key, AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values, AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key, LongWritable> {

        public void reduce(AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Key key,
                           Iterable<AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values> values,
                           Context context) throws IOException, InterruptedException {

            long summingPrice = 0;
            long summingCount = 0;
            for (AveragePriceOfCommoditiesPerUnitInBrazilExportationWritable_Values o : values) {
                summingPrice += o.getPrice();
                summingCount += o.getCont();
            }


            context.write(key, new LongWritable(summingPrice/summingCount));
        }
    }



}
