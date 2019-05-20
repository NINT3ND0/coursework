
class PrecisedComp {

    private double A;
    private double B;
    double a;
    double b;

    double[][] Result;
    double hStep;
    double tauStep;
    int xNodes;
    int tNodes;


    PrecisedComp(double a, double b, double A, double B, int tNodes, int xNodes){
        this.a = a;
        this.b = b;
        this.A = A;
        this.B = B;
        this.xNodes = xNodes;
        this.tNodes = tNodes;
        this.hStep = 1.0 / xNodes;
        this.tauStep = 1.0 / tNodes;
        this.Result =  new double[tNodes][xNodes];
    }

    double getValue(double x, double t){
        double F = (3 * B - 2 * a * t) / 3;
        double tempResult = (Math.pow(x, 2) / F) + (A * Math.pow(F,-3)) + ((9 * b * F )/ (8 * a));

        return (1.0 / Math.cbrt(tempResult));
    }

    double[][] getResult()
    {
        double t = 0;
        for(int i = 0; i < tNodes; i++){
            double x = 0;
            for(int j = 0; j < xNodes; j++){
                Result[i][j] = getValue(x, t);
                x += hStep;
            }
            t += tauStep;
        }
        return Result;
    }


}
