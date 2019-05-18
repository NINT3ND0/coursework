
public class PrecisedResult {

    public double getValue(double x, double t){
        double part1 = 33 - 1.8 * t;
        double part2 = (3 * Math.pow(x, 2)) / part1
                        + 64 * 27 / Math.pow(part1,3)
                        + part1 / 3;

        return 1 / Math.pow(part2, 1/3);
    }

}
