public class Main {

    public static void main(String[] args) {
        int xNodes = 4;
        int tNodes = 32;
        double a = 9;
        double b = 8;
        double A = 20;
        double B = 10;

        SequentialComp seqComp = new SequentialComp(xNodes, tNodes, a, b, A, B);
        PrecisedComp precisedComp = new PrecisedComp(a, b, A, B, tNodes, xNodes);
        ParallelComp paralComp = new ParallelComp(xNodes, tNodes, a, b, A, B);


        double[][] seqResult = seqComp.computeResult();
        double[][] precisedResult = precisedComp.computeResult();
        double[][] paralResult = paralComp.computeResult();

        for(int i = 0; i < tNodes; i++){
            for(int j =0; j < xNodes;j++){
                System.out.print(seqResult[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println();


        for(int i = 0; i < tNodes; i++){
            for(int j =0; j < xNodes;j++){
                System.out.print(precisedResult[i][j] + "|");
            }
            System.out.println();
        }

        System.out.println();


        for(int i = 0; i < tNodes; i++){
            for(int j =0; j < xNodes;j++){
                System.out.print(paralResult[i][j] + "|");
            }
            System.out.println();
        }

    }
}
