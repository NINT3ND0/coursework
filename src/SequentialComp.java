class SequentialComp extends PrecisedComp{

    SequentialComp(int xNodes, int tNodes, double a, double b, double A, double B){
        super(a, b, A, B, tNodes, xNodes);
    }

    private double compute(int i, int j){
        double curr = Result[i-1][j];
        double prev = Result[i-1][j-1];
        double next = Result[i-1][j+1];

        double part1 = ( -3 * a * Math.pow(curr, -4) * Math.pow((next - prev) / (2*hStep), 2)) ;
        double part2 = (a * Math.pow(curr, 3) * (prev - 2 * curr + next)) / Math.pow(hStep, 2) + b * Math.pow(curr, 4);
        double part3 = tauStep * (part1 + part2);

        return curr + part3;
    }

    double[][] getResult()
    {
        double x = 0;
        for(int i = 0; i < xNodes; i++){
            Result[0][i] = this.getValue( x, 0 );
            x += hStep;
        }

        double t = tauStep;
        for(int i = 1; i < tNodes; i++){

            Result[i][0] = this.getValue(0, t);
            Result[i][xNodes - 1] = this.getValue( 1, t);
            t += tauStep;

            for(int j = 1; j < xNodes - 1; j++){
                //seqResult[i][j] = precisedValue.getValue(i*tauStep, j*hStep);
                Result[i][j] = compute(i, j);
            }
        }
        return Result;
    }

}
