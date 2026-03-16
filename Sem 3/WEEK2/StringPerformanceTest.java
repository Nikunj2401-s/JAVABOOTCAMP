package javabootcamp.WEEK2;

public class StringPerformanceTest {
    public static void main(String[] args) {
        int iterations = 100000;

        long start, end;

        start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < iterations; i++) {
            s += "a";
        }
        end = System.currentTimeMillis();
        System.out.println("String time: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("a");
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuilder time: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbf.append("a");
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuffer time: " + (end - start) + " ms");
    }
}
