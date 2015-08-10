package ui_tests.lesson_2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class isPrime {

    public boolean isPrimeTest(int testValue) {
        int i = 2;
        while (i < testValue) {
            if (testValue % i == 0) return false;
            i++;
        }
        return true;
    }

    @Test
    public void valueTest() {
        Assert.assertTrue(isPrimeTest(7));
    }
}
