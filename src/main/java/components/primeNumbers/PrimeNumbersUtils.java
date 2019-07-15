package components.primeNumbers;

public final class PrimeNumbersUtils
{
    public static int countPrimesWithinRange(int from, int to)
    {
        int counter = 0;
        for (int i = from; i <= to; i++)
        {
            if (i == 0 || i == 1)
            {
                continue;
            }
            else if (i == 2 || i == 3)
            {
                counter++;
                continue;
            }
            else
            {
                if (isNumberPrime(i))
                {
                    counter++;
                    continue;
                }
            }
        }
        return counter;
    }

    private static boolean isNumberPrime(int number)
    {
        for (int i = 2; i < number; i++)
        {
            if (number % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    private PrimeNumbersUtils()
    {
    }
}
