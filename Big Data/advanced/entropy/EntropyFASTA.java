package advanced.entropy;

import jdk.nashorn.internal.scripts.JO;
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

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.LinkedList;

public class EntropyFASTA {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        BasicConfigurator.configure();

        Configuration c = new Configuration();
        String[] files = new GenericOptionsParser(c, args).getRemainingArgs();
        Path input = new Path("./in/JY157487.1.fasta");
        Path intermediate = new Path("./output/intermediate.tmp");
        Path output = new Path("./output/entropia.txt");

        Job j1 = new Job(c, "contagem");

        j1.setJarByClass(EntropyFASTA.class);
        j1.setMapperClass(MapEtapaA.class);
        j1.setReducerClass(ReduceEtapaA.class);

        j1.setMapOutputKeyClass(Text.class);
        j1.setMapOutputValueClass(LongWritable.class);
        j1.setOutputKeyClass(Text.class);
        j1.setOutputValueClass(LongWritable.class);

        FileInputFormat.addInputPath(j1, input);
        FileOutputFormat.setOutputPath(j1, intermediate);

        if (!j1.waitForCompletion(true)) {
            System.err.println("Deu erro o job 1");
            System.exit(1);
        }

        Job j2 = new Job(c, "entropia");

        j2.setJarByClass(EntropyFASTA.class);
        j2.setMapperClass(MapEtapaB.class);
        j2.setReducerClass(ReduceEtapaB.class);

        j2.setMapOutputKeyClass(Text.class);
        j2.setMapOutputValueClass(BaseQtdWritable.class);
        j2.setOutputKeyClass(Text.class);
        j2.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(j2, intermediate);
        FileOutputFormat.setOutputPath(j2, output);

        if (!j2.waitForCompletion(true)) {
            System.err.println("Deu erro o job 2");
            System.exit(1);
        }
    }


    public static class MapEtapaA extends Mapper<LongWritable, Text, Text, LongWritable> {
        public void map(LongWritable key, Text value, Context con)
                throws IOException, InterruptedException {
            String linha = value.toString();

            if (linha.startsWith(">")) return;
            char[] caracteres = linha.toCharArray();

            for (char c : caracteres){
                con.write(new Text(String.valueOf(c)), new LongWritable(1));
                con.write(new Text("total"), new LongWritable(1));
            }

        }
    }

    public static class ReduceEtapaA extends Reducer<Text, LongWritable, Text, LongWritable> {
        public void reduce(Text key, Iterable<LongWritable> values, Context con)
                throws IOException, InterruptedException {

            long soma = 0;
            for (LongWritable i : values)
                soma += i.get();
            con.write(key, new LongWritable(soma));

        }
    }


    public static class MapEtapaB extends Mapper<LongWritable, Text, Text, BaseQtdWritable> {
        public void map(LongWritable key, Text value, Context con)
                throws IOException, InterruptedException {

            String linha = value.toString(); // A 125

            String[] campos = linha.split("\t");
            String chave = campos[0];
            long qtd = Long.parseLong(campos[1]);

            con.write(new Text("entropia"), new BaseQtdWritable(chave, qtd));

        }
    }

    public static class ReduceEtapaB extends Reducer<Text, BaseQtdWritable, Text, DoubleWritable> {
        public void reduce(Text key, Iterable<BaseQtdWritable> values, Context con)
                throws IOException, InterruptedException {

            LinkedList<BaseQtdWritable> lista = new LinkedList<>();
            for (BaseQtdWritable o : values)
                lista.add(new BaseQtdWritable(o.getTexto(), o.getQtd()));

            long qtdtotal = Long.MIN_VALUE;
            for (BaseQtdWritable o : lista)
                if (o.getTexto().equals("total")){
                    qtdtotal = o.getQtd();
                    break;
                }

            for (BaseQtdWritable o : lista)
                if (!o.getTexto().equals("total")){
                    long qtdCaracter = o.getQtd();
                    double prob = qtdCaracter / (double) qtdtotal;
                    double entropia = -prob * (Math.log10(prob)/Math.log10(2.0));
                    con.write(new Text(o.getTexto()), new DoubleWritable(entropia));
                }
        }
    }

}
