package ui_tests.lesson_2;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PrimeTest {

    public int testValue = 7;

    @Test
    public void isPrimeRunnerTest() {
        Assert.assertTrue(isPrime(testValue));
    }

    public boolean isPrime(int testValue) {
        int i, count=0;
        if (testValue < 2) return false;
        for (i=2; i<testValue; i++) {
            if (testValue%i==0) { return false;}
        }

        return true;
    }
}
