class SequentialComp extends PrecisedComp{

    SequentialComp(int xNodes, int tNodes, double a, double b, double A, double B){
        super(a, b, A, B, tNodes, xNodes);
    }

    private double computeValue(int i, int j){
        double current = result[i-1][j];
        double prev = result[i-1][j-1];
        double next = result[i-1][j+1];

        double part1 = ( -3 * a * Math.pow(current, -4) * Math.pow((next - prev) / (2*hStep), 2)) ;
        double part2 = (a * Math.pow(current, 3) * (prev - 2 * current + next)) / Math.pow(hStep, 2) + b * Math.pow(current, 4);
        double part3 = tauStep * (part1 + part2);

        return current + part3;
    }

    double[][] computeResult()
    {
        double x = 0;
        for(int i = 0; i < xNodes; i++){
            result[0][i] = this.computePrecValue( x, 0 );
            x += hStep;
        }

        double t = tauStep;
        for(int i = 1; i < tNodes; i++){

            result[i][0] = this.computePrecValue(0, t);
            result[i][xNodes - 1] = this.computePrecValue( 1, t);
            t += tauStep;

            for(int j = 1; j < xNodes - 1; j++){
                //seqResult[i][j] = precisedValue.getValue(i*tauStep, j*hStep);
                result[i][j] = computeValue(i, j);
            }
        }
        return result;
    }

}
