
class PrecisedComp {

    PrecisedComp(double a, double b, double A, double B, int tNodes, int xNodes){
        this.a = a;
        this.b = b;
        this.A = A;
        this.B = B;
        this.xNodes = xNodes;
        this.tNodes = tNodes;
        this.hStep = 1.0 / xNodes;
        this.tauStep = 1.0 / tNodes;
        this.result =  new double[tNodes][xNodes];
    }


    double[][] computeResult()
    {
        for(int i = 0; i < tNodes; i++){
            for(int j = 0; j < xNodes; j++){
                result[i][j] = computePrecValue(j * hStep, i * tauStep);
            }
        }
        return result;
    }


    double computePrecValue(double x, double t){
        double F = (3 * B - 2 * a * t) / 3;
        double tempResult = (Math.pow(x, 2) / F) + (A * Math.pow(F,-3)) + ((9 * b * F )/ (8 * a));

        return (1.0 / Math.cbrt(tempResult));
    }


    private double A;
    private double B;
    double a;
    double b;
    double[][] result;
    double hStep;
    double tauStep;
    int xNodes;
    int tNodes;
}
