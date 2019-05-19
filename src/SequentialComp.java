class SequentialComp {

    private double[][] seqResult;
    private double hStep;
    private double tauStep;

    SequentialComp(int xNodes, int tNodes){
        this.hStep = (1.0/xNodes);
        this.tauStep = (1.0/tNodes);
        this.seqResult =  new double[tNodes][xNodes];

        PrecisedComp precisedValue = new PrecisedComp();

        double x = 0;
        for(int i = 0; i < xNodes; i++){
            seqResult[0][i] = precisedValue.getValue( x, 0 );
            x += hStep;
        }

        double t = tauStep;
        for(int i = 1; i < tNodes; i++){

            seqResult[i][0] = precisedValue.getValue(0, t);
            seqResult[i][xNodes - 1] = precisedValue.getValue( 1, t);
            t += tauStep;

            for(int j = 1; j < xNodes - 1; j++){
                seqResult[i][j] = computing(i, j);
            }
        }
    }

    private double computing(int i, int j){
        double curr = seqResult[i-1][j];
        double prev = seqResult[i-1][j-1];
        double next = seqResult[i-1][j+1];

        double part1 = (-2.7 * Math.pow((next - prev) / (2*hStep), 2)) / Math.pow(curr, 4);
        double part2 = (0.9 * (prev - 2 * curr + next)) / (Math.pow(hStep, 2) *  Math.pow(curr, 3))
                        + 0.8 * Math.pow(curr, 4);
        double part3 = tauStep * (part1 + part2);
        double tmp = curr + part3;
        System.out.println("(" + i + ";" + j + ")" + curr + " | " + prev + " | " + next + " | " + part1 + " | " + part2 + " | " + part3 + " | " + tmp);
        return curr + part3;
    }

    public double[][] getResult()
    {
        return seqResult;
    }

}
