package components.primeNumbers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class PrimeNumberForkJoin extends RecursiveTask<Integer>
{
    private static final Logger logger = LoggerFactory.getLogger(PrimeNumberForkJoin.class);

    public static final int MAX_NUMBERS_PER_THREAD = 20000;

    private int startNumber;
    private int endNumber;

    public PrimeNumberForkJoin(int max)
    {
        this(2, max);
    }

    private PrimeNumberForkJoin(int start, int end)
    {
        startNumber = start;
        endNumber = end;
    }

    @Override
    protected Integer compute()
    {
        if (endNumber - startNumber <= MAX_NUMBERS_PER_THREAD)
        {
            return PrimeNumbersUtils.countPrimesWithinRange(startNumber, endNumber);
        }

        int splitter = (endNumber - startNumber) / 2;

        PrimeNumberForkJoin subTaskOne = new PrimeNumberForkJoin(startNumber, startNumber + splitter);
        PrimeNumberForkJoin subTaskTwo = new PrimeNumberForkJoin(startNumber + splitter + 1, endNumber);
        subTaskTwo.fork();
        int res = subTaskOne.compute();
        int res2 = subTaskTwo.join();

        return res + res2;
    }

    public static int start(int max)
    {
        logger.debug("Started counting prime numbers in range [0, " + max + "]");
        ForkJoinTask<Integer> task = new PrimeNumberForkJoin(max);
        return new ForkJoinPool().invoke(task);
    }

}