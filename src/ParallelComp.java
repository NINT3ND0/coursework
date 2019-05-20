import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;


class ParallelComp extends PrecisedComp{

    ParallelComp(int xNodes, int tNodes, double a, double b, double A, double B) {
        super(a, b, A, B, tNodes, xNodes);
    }

    double[][] computeResult()
    {
        for(int i = 0; i < tNodes; i++) {
            int indexI = i;
            AtomicInteger atomicIndexJ = new AtomicInteger();

            DoubleStream.of(result[i]).parallel().forEach(arrayElement -> {
                int oldIndexJ;
                int newIndexJ;
                do {
                    oldIndexJ = atomicIndexJ.get();
                    newIndexJ = oldIndexJ + 1;
                } while(!atomicIndexJ.compareAndSet(oldIndexJ, newIndexJ));
                newIndexJ--;


                if(indexI != 0 && newIndexJ != 0 && newIndexJ != (xNodes - 1)){
                    double current = result[indexI - 1][newIndexJ];
                    double prev = result[indexI - 1][newIndexJ - 1];
                    double next = result[indexI - 1][newIndexJ + 1];

                    double part1 = (-3 * a * Math.pow(current, -4) * Math.pow((next - prev) / (2 * hStep), 2));
                    double part2 = (a * Math.pow(current, 3) * (prev - 2 * current + next)) / Math.pow(hStep, 2) + b * Math.pow(current, 4);
                    double part3 = tauStep * (part1 + part2);

                    result[indexI][newIndexJ] = current + part3;
                }
                else {
                    result[indexI][newIndexJ] = this.computePrecValue(newIndexJ * hStep , indexI * tauStep );
                }

            });

        }

        return result;
    }



}



