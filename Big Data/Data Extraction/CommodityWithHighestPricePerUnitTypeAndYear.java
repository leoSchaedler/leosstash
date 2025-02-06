package br.puc.pr.TDE02.atv06;

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

public class CommodityWithHighestPricePerUnitTypeAndYear {

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
        j.setJarByClass(CommodityWithHighestPricePerUnitTypeAndYear.class);
        j.setMapperClass(CommodityWithHighestPricePerUnitTypeAndYear.TDEMapper.class);
        j.setReducerClass(CommodityWithHighestPricePerUnitTypeAndYear.TDEReducer.class);
        j.setCombinerClass(CommodityWithHighestPricePerUnitTypeAndYear.TDECombiner.class);

        // tipos de chave e valor de saida
//          map
        j.setMapOutputKeyClass(CommodityWithHighestPricePerUnitTypeAndYearWritable_Key.class);
        j.setMapOutputValueClass(CommodityWithHighestPricePerUnitTypeAndYearWritable_Values.class);
//          reduce
        j.setOutputKeyClass(CommodityWithHighestPricePerUnitTypeAndYearWritable_Key.class);
        j.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(j, input);
        FileOutputFormat.setOutputPath(j, output);

        System.exit(j.waitForCompletion(true) ? 0 : 1);
    }

    public static class TDEMapper extends Mapper<Object, Text, CommodityWithHighestPricePerUnitTypeAndYearWritable_Key, CommodityWithHighestPricePerUnitTypeAndYearWritable_Values> {
        public void map(Object key, Text value, Context context) throws IOException,
                InterruptedException {

            String campos[] = value.toString().split(";");

            if (!value.toString().contains("country_or_area")) {

                //key -> CommodityWithHighestPricePerUnitTypeAndYearWritable_Key
                String unitType = campos[7];
                String year = campos[1];

                //values -> CommodityWithHighestPricePerUnitTypeAndYearWritable_Values
                String commodity = campos[3].split(",")[0];
                double price = Double.parseDouble(campos[5]);

                context.write(new CommodityWithHighestPricePerUnitTypeAndYearWritable_Key(unitType, year), new CommodityWithHighestPricePerUnitTypeAndYearWritable_Values(commodity, price));
            }
        }
    }

    public static class TDECombiner extends Reducer<CommodityWithHighestPricePerUnitTypeAndYearWritable_Key, CommodityWithHighestPricePerUnitTypeAndYearWritable_Values, CommodityWithHighestPricePerUnitTypeAndYearWritable_Key, CommodityWithHighestPricePerUnitTypeAndYearWritable_Values> {

        public void combiner(CommodityWithHighestPricePerUnitTypeAndYearWritable_Key key,
                             Iterable<CommodityWithHighestPricePerUnitTypeAndYearWritable_Values> values,
                             Context context) throws IOException, InterruptedException {

            double summingPrice = 0;
            for (CommodityWithHighestPricePerUnitTypeAndYearWritable_Values o : values) {
                summingPrice += o.getPrice();
                context.write(key, new CommodityWithHighestPricePerUnitTypeAndYearWritable_Values(o.getCommodity(), summingPrice));
            }

        }
    }

    public static class TDEReducer extends Reducer<CommodityWithHighestPricePerUnitTypeAndYearWritable_Key, CommodityWithHighestPricePerUnitTypeAndYearWritable_Values, CommodityWithHighestPricePerUnitTypeAndYearWritable_Key, Text> {

        public void reduce(CommodityWithHighestPricePerUnitTypeAndYearWritable_Key key,
                           Iterable<CommodityWithHighestPricePerUnitTypeAndYearWritable_Values> values,
                           Context context) throws IOException, InterruptedException {

            double comparePrice = Double.MIN_VALUE;
            String highestPrice = "";
            for (CommodityWithHighestPricePerUnitTypeAndYearWritable_Values o : values)
                if (o.getPrice() > comparePrice){
                    comparePrice = o.getPrice();
                    highestPrice = o.getCommodity();
                }


            context.write(key, new Text(highestPrice));
        }
    }

}
