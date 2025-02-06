package br.pucpr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Tempos {

    //Tempo -> Gerando o array
    private long tGerar1 = 0;
    private long tGerar2 = 0;
    private long tGerarDelta;

    //Tempo -> Processamento
    private long tCalculo1 = 0;
    private long tCalculo2 = 0;
    private long tCalculoDelta;

    //File
    private final String path;
    private BufferedWriter timeFile;

    public Tempos() throws IOException {
        clearAll();
        this.path = "C:\\Users\\User\\OneDrive\\IDE\\InteliJ\\programacaoDistribuidaParalelaEConcorrente\\exercicioParalelismoRecursivo_entrega\\src\\br\\pucpr\\TimeFiles\\timeFile.csv";
        File file = new File(path);
        if (!file.exists()) file.createNewFile();
        this.timeFile = new BufferedWriter(new FileWriter(file));
    }

    public void settCalculo1(long tCalculo1) {
        this.tCalculo1 = tCalculo1;
    }

    public void settCalculo2(long tCalculo2) {
        this.tCalculo2 = tCalculo2;
    }

    public void settGerar1(long tGerar1) {
        this.tGerar1 = tGerar1;
    }

    public void settGerar2(long tGerar2) {
        this.tGerar2 = tGerar2;
    }

    private void clearAll(){
        File folderFinal = new File("C:\\Users\\User\\OneDrive\\IDE\\InteliJ\\programacaoDistribuidaParalelaEConcorrente\\exercicioParalelismoRecursivo_entrega\\src\\br\\pucpr\\TimeFiles");
        File[] listFinalFiles = folderFinal.listFiles();
        for (File f : listFinalFiles)
            if (f.exists()) f.delete();
    }

    public void calcularDelta(){
        this.tGerarDelta = tGerar2 - tGerar1;
        this.tCalculoDelta = tCalculo2 - tCalculo1;
    }

    public void escrever(String id, String size) throws IOException {
        timeFile.append(id + ";" + size + ";" + tGerarDelta + ";" + tCalculoDelta);
        timeFile.newLine();
    }

    public void close() throws IOException {
        timeFile.close();
    }

}
