package components.primeNumbers;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class PrimeNumbersThreadPoolExecutor
{
    private static final Logger logger = LoggerFactory.getLogger(PrimeNumbersThreadPoolExecutor.class);

    private int maxNumber;
    private Set<Future<Integer>> futures = new HashSet<>();

    public PrimeNumbersThreadPoolExecutor(int maxNumber)
    {
        this.maxNumber = maxNumber;
    }

    public int execute() throws InterruptedException, ExecutionException
    {
        int poolSize = Runtime.getRuntime().availableProcessors() + 1;
        logger.debug("Started executing with pool size = " + poolSize);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());

        int maxProcessedNumber = 0;
        int batchSize = maxNumber / poolSize;
        for (int i = 1; i <= poolSize; i++)
        {
            if (maxProcessedNumber == 0)
            {
                Future<Integer> future = threadPoolExecutor.submit(new MyTask(2, batchSize));
                futures.add(future);
                maxProcessedNumber = batchSize;
            }
            else
            {
                if (i == poolSize)
                {
                    Future<Integer> future = threadPoolExecutor.submit(new MyTask(maxProcessedNumber + 1, maxNumber));
                    futures.add(future);
                }
                else
                {
                    int upperBound = maxProcessedNumber + batchSize;
                    Future<Integer> future = threadPoolExecutor.submit(new MyTask(maxProcessedNumber + 1, upperBound));
                    futures.add(future);
                    maxProcessedNumber = upperBound;
                }
            }
        }
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(10, TimeUnit.MINUTES);

        int finalResult = 0;
        for (Future<Integer> f : futures)
        {
            finalResult += f.get();
        }
        logger.debug("Found " + finalResult + " primes");
        return finalResult;
    }

    @AllArgsConstructor
    private static class MyTask implements Callable<Integer>
    {
        private int from;
        private int to;

        @Override
        public Integer call()
        {
            return PrimeNumbersUtils.countPrimesWithinRange(from, to);
        }
    }
}
