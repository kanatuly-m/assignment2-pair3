package cli;

import algorithms.Kadane;
import algorithms.MajorityVote;
import algorithms.Result;
import metrics.PerformanceTracker;

import java.nio.file.*;
import java.util.*;

public class BenchmarkRunner {
    public static void main(String[] args) throws Exception {
        Map<String, String> p = parseArgs(args);

        String algo  = p.getOrDefault("algo", "kadane");    // kadane | majority
        int n        = Integer.parseInt(p.getOrDefault("n", "1000"));
        String dist  = p.getOrDefault("dist", "random");    // random | equal
        int trials   = Integer.parseInt(p.getOrDefault("trials", "3"));
        boolean print= Boolean.parseBoolean(p.getOrDefault("print", "false"));

        Path outDir = Paths.get("docs", "performance-plots");
        Files.createDirectories(outDir);
        Path csv = outDir.resolve(algo + "-n" + n + "-" + dist + ".csv");

        boolean writeHeader = !Files.exists(csv);
        try (var writer = Files.newBufferedWriter(csv,
                java.nio.charset.StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            PerformanceTracker t = new PerformanceTracker();
            if (writeHeader) writer.write(PerformanceTracker.csvHeader() + System.lineSeparator());

            Random rnd = new Random(42);
            for (int trial = 1; trial <= trials; trial++) {
                int[] a = generateArray(n, dist, rnd);
                t.reset();

                if (algo.equalsIgnoreCase("kadane")) {
                    Result r = Kadane.kadane(a, t);
                    if (print) System.out.println("Kadane: sum=" + r.maxSum + " [" + r.left + ".." + r.right + "]");
                } else if (algo.equalsIgnoreCase("majority")) {
                    Integer x = MajorityVote.findMajority(a, t);
                    if (print) System.out.println("Majority: " + x);
                } else {
                    throw new IllegalArgumentException("Unknown algo: " + algo);
                }

                writer.write(t.toCsvRow(algo, n, dist, trial) + System.lineSeparator());
            }
        }
        System.out.println("CSV written to: " + csv.toAbsolutePath());
    }

    private static int[] generateArray(int n, String dist, Random rnd) {
        int[] a = new int[n];
        switch (dist.toLowerCase()) {
            case "equal" -> {
                // половина одного значения, половина другого (есть большинство)
                int majority = 1, other = 0;
                for (int i = 0; i < n; i++) a[i] = (i < (n/2 + 1)) ? majority : other;
            }
            case "random" -> {
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt(21) - 10; // -10..10
            }
            default -> throw new IllegalArgumentException("Unknown dist: " + dist);
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
