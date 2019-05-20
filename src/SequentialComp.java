class SequentialComp extends PrecisedComp{

    SequentialComp(int xNodes, int tNodes, double a, double b, double A, double B){
        super(a, b, A, B, tNodes, xNodes);
    }

    double[][] computeResult()
    {

        for(int i = 0; i < xNodes; i++){
            result[0][i] = this.computePrecValue( i * hStep, 0 );
        }

        for(int i = 1; i < tNodes; i++){

            result[i][0] = this.computePrecValue(0.0, i * tauStep);
            result[i][xNodes - 1] = this.computePrecValue( 1.0, i * tauStep);

            for(int j = 1; j < xNodes - 1; j++){
                result[i][j] = computeValue(i, j);
            }
        }
        return result;
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



}
