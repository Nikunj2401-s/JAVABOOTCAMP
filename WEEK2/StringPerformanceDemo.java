package javabootcamp.WEEK2;

public class StringPerformanceDemo {
    public static void main(String[] args) {
        int iterations = 100000; // number of append operations
        long start, end;

        // --- Using String ---
        String str = "";
        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            str += "a"; // creates a new object each time
        }
        end = System.currentTimeMillis();
        System.out.println("Time taken with String: " + (end - start) + " ms");

        // --- Using StringBuilder ---
        StringBuilder sb = new StringBuilder();
        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            sb.append("a"); // modifies the same object
        }
        end = System.currentTimeMillis();
        System.out.println("Time taken with StringBuilder: " + (end - start) + " ms");

        // --- Using StringBuffer ---
        StringBuffer sbuf = new StringBuffer();
        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            sbuf.append("a"); // thread-safe but slower
        }
        end = System.currentTimeMillis();
        System.out.println("Time taken with StringBuffer: " + (end - start) + " ms");
    }
}

