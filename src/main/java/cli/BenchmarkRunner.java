package cli;

import algorithms.Kadane;
import algorithms.Result;
import metrics.PerformanceTracker;

import java.nio.file.*;
import java.util.*;

public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
        Map<String, String> params = parseArgs(args);
        int n = Integer.parseInt(params.getOrDefault("n", "1000"));
        String dist = params.getOrDefault("dist", "random");
        int trials = Integer.parseInt(params.getOrDefault("trials", "3"));
        String csvPath = params.getOrDefault("csv", "docs/performance-plots/kadane.csv");
        boolean print = Boolean.parseBoolean(params.getOrDefault("print", "false"));
        Random rnd = new Random(42);

        Path out = Paths.get(csvPath);
        Files.createDirectories(out.getParent());
        boolean newFile = !Files.exists(out);
        if (newFile) Files.writeString(out, new PerformanceTracker().toCsvHeader() + "\n");

        for (int trial = 1; trial <= trials; trial++) {
            int[] a = generateArray(n, dist, rnd);
            PerformanceTracker t = new PerformanceTracker();
            Result r = Kadane.kadane(a, t);
            Files.writeString(out, t.toCsvRow("kadane", n, dist, trial) + "\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            if (print) System.out.println("Trial " + trial + ": " + r);
        }
    }

    private static int[] generateArray(int n, String dist, Random rnd) {
        int[] a = new int[n];
        switch (dist) {
            case "all-negative":
                for (int i = 0; i < n; i++) a[i] = -rnd.nextInt(100) - 1;
                break;
            case "all-equal":
                int val = rnd.nextInt(21) - 10;
                Arrays.fill(a, val);
                break;
            default: // random
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt(21) - 10;
        }
        return a;
    }

    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (String s : args) {
            if (s.startsWith("--") && s.contains("=")) {
                int p = s.indexOf('=');
                map.put(s.substring(2, p), s.substring(p + 1));
            }
        }
        return map;
    }
}
