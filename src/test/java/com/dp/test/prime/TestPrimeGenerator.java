package com.dp.test.prime;


import com.dp.prime.PrimeGenerator;
import junit.framework.TestCase;
import org.junit.Test;

public class TestPrimeGenerator extends TestCase {

    @Test
    public void testPrimes(){
        int[] nullArray = PrimeGenerator.generatePrimes(0);
        assertEquals(nullArray.length,0);

        int[] minArray = PrimeGenerator.generatePrimes(2);
        assertEquals(minArray.length,1);
        assertEquals(minArray[0],2);

        int[] threeArray = PrimeGenerator.generatePrimes(3);
        assertEquals(threeArray.length,2);
        assertEquals(threeArray[0],2);
        assertEquals(threeArray[1],3);

        int[] centArray = PrimeGenerator.generatePrimes(100);
        assertEquals(centArray.length,25);
        assertEquals(centArray[24],97);
    }
}
