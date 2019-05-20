import java.io.*;

public class Main {

    private static int xNodes = 4;
    private static int tNodes = 32;

    public static void main(String[] args) {

        double a = 9;
        double b = 8;
        double A = 20;
        double B = 10;

        SequentialComp seqComp = new SequentialComp(xNodes, tNodes, a, b, A, B);
        PrecisedComp precisedComp = new PrecisedComp(a, b, A, B, tNodes, xNodes);
        ParallelComp paralComp = new ParallelComp(xNodes, tNodes, a, b, A, B);


        long startSeqTime = System.nanoTime();
        double[][] seqResult = seqComp.computeResult();
        long totalSeqTime = System.nanoTime() - startSeqTime;
        writeToFile("Sequential.txt", seqResult);

        long startPrecTime = System.nanoTime();
        double[][] precisedResult = precisedComp.computeResult();
        long totalPrecTime = System.nanoTime() - startPrecTime;
        writeToFile("Precised.txt", precisedResult);

        long startParalTime = System.nanoTime();
        double[][] paralResult = paralComp.computeResult();
        long totalParalTime = System.nanoTime() - startParalTime;
        writeToFile("Parallel.txt", paralResult);

        double[][] differencePar = calculateDifference(paralResult, precisedResult);
        writeToFile("ParDifference.txt", differencePar);
        double[][] differenceSeq = calculateDifference(seqResult, precisedResult);
        writeToFile("SeqDifference.txt", differenceSeq);
        double[][] differenceParSeq = calculateDifference(paralResult, seqResult);
        writeToFile("ParSeqDifference.txt", differenceParSeq);

        System.out.println("Seq: " + totalSeqTime + " Prec: " + totalPrecTime + " Paral: " + totalParalTime);

    }

    private static void writeToFile(String filename, double[][] result) {
        try {
            File file = new File(filename);
            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < tNodes; i++) {
                for (int j = 0; j < xNodes; j++) {
                    String value = String.valueOf(result[i][j]);
                    value += "; ";
                    fileWriter.write(value);
                }
                fileWriter.write("\n");
            }
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private static double[][] calculateDifference(double[][] paralResult, double[][] precisedResult){
        double[][] difference = new double[tNodes][xNodes];

        for (int i = 0; i < tNodes; i++) {
            for (int j = 0; j < xNodes; j++) {
               difference[i][j] = precisedResult[i][j] - paralResult[i][j];
            }
        }
        return difference;
    }

}
