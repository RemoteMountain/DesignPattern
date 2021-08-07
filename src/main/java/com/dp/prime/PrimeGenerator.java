package com.dp.prime;

public class PrimeGenerator {
    private static boolean[] isCrossed;
    private static int[] result;

    public static int[] generatePrimes(int maxValue) {
        if (maxValue < 2) {
            return new int[0];
        }
        initializeArrayOfIntegers(maxValue);
        crossOutMultiples();
        putUncrossedIntegerIntoResult();
        return result;
    }

    private static void putUncrossedIntegerIntoResult() {
        int i;
        int j;

        int count = 0;
        for (i = 0; i < isCrossed.length; i++) {
            if (isCrossed[i]) {
                count++;
            }
        }
        result = new int[count];

        for (i = 0, j = 0; i < isCrossed.length; i++) {
            if (isCrossed[i]) {
                result[j++] = i;
            }

        }
    }

    private static void crossOutMultiples() {
        int i;
        int j;
        for (i = 2; i < Math.sqrt(isCrossed.length) + 1; i++) {
            for (j = 2 * i; j < isCrossed.length; j += i) {
                isCrossed[j] = false;
            }
        }
    }

    private static void initializeArrayOfIntegers(int maxValue) {
        isCrossed = new boolean[maxValue + 1];
        for (int i = 2; i < isCrossed.length; i++) {
            isCrossed[i] = false;
        }

    }
}
