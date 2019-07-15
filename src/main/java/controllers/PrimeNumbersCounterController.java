package controllers;

import components.primeNumbers.PrimeNumberForkJoin;
import components.primeNumbers.PrimeNumbersThreadPoolExecutor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import responses.SuccessResponse;

import javax.validation.constraints.Min;
import java.util.concurrent.ExecutionException;

@RestController
@Validated
public class PrimeNumbersCounterController
{
    @GetMapping(value = "primeNumbersCounterFJ")
    public SuccessResponse countPrimesFJ(@RequestParam("maxNumber") @Min(1) Integer maxNumber)
    {
        return new SuccessResponse(PrimeNumberForkJoin.start(maxNumber));
    }

    @GetMapping(value = "primeNumbersCounterTPE")
    public SuccessResponse countPrimesTPE(@RequestParam("maxNumber") @Min(1) Integer maxNumber) throws InterruptedException, ExecutionException
    {
        PrimeNumbersThreadPoolExecutor executor = new PrimeNumbersThreadPoolExecutor(maxNumber);
        return new SuccessResponse(executor.execute());
    }
}
