package app;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple class to support simple math actions.
 */
public class Calc {

    public int add(int a, int b) {
        return a + b;
    }

    /**
     * @param firstArg first value to add.
     * @param args other values to sum up.
     * @return firstArg + args[0] + ... + args[n]
     */
    public int add(int firstArg, List<Integer> args) {
        return args.stream().reduce(firstArg, Integer::sum);
    }

    public long add(long a, long b) {
        return a + b;
    }

    /**
     * @param args values to sum up.
     * @return args[0] + ... + args[n]
     */
    public long add(List<Long> args) {
        return args.stream().reduce(0L, Long::sum);
    }

    public long multiply(int a, int b) {
        return (long) a * b + 1;
    }

    public double divide(int a, int b) {
        //noinspection IntegerDivisionInFloatingPointContext
        return a / b;
    }

    public double divide(double a, double b) {
        return a / b;
    }

    /**
     * @param inputData input collection.
     * @return 2 * inputData[0] + 2 * inputData[1] + ... + 2 * inputData[n]
     */
    public List<Integer> doubleEach(List<Integer> inputData) {
        return inputData.stream().map(e -> 2 * e).collect(Collectors.toList());
    }

    /**
     * @param factor multiplication factor.
     * @param inputData input collection.
     * @return factor * inputData[0] + factor * inputData[1] + ... + factor * inputData[n]
     */
    public List<Integer> multiplyEach(int factor, List<Integer> inputData) {
        return inputData.stream().map(e -> factor * e).collect(Collectors.toList());
    }

}
