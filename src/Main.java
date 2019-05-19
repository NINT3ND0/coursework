public class Main {

    public static void main(String[] args) {
        int xNodes = 4;
        int tNodes = 32;

        SequentialComp seqResult = new SequentialComp(xNodes, tNodes);
        double[][] seqCompResult = seqResult.getResult();

  /*      for(int i = 0; i < tNodes; i++){
            for(int j =0; j < xNodes;j++){
                System.out.print(seqCompResult[i][j] + "|");
            }
            System.out.println();
        }*/


    }
}
