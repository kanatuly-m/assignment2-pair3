package metrics;

public class PerformanceTracker {
    private long comparisons;
    private long arrayAccesses;
    private long assignments;
    private long allocations;
    private long startNs;
    private long elapsedNs;

    public void startTimer() { startNs = System.nanoTime(); }
    public void stopTimer()  { elapsedNs = System.nanoTime() - startNs; }

    public void incComparisons()   { comparisons++; }
    public void incArrayAccesses() { arrayAccesses++; }
    public void incAssignments()   { assignments++; }
    public void incAllocations()   { allocations++; }

    public long getComparisons()   { return comparisons; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getAssignments()   { return assignments; }
    public long getAllocations()   { return allocations; }
    public long getElapsedNs()     { return elapsedNs; }

    public String toCsvHeader() {
        return "algo,n,dist,trial,time_ms,comparisons,array_accesses,assignments,allocations";
    }
    public String toCsvRow(String algo, int n, String dist, int trial) {
        double ms = elapsedNs / 1_000_000.0;
        return String.join(",",
                algo, String.valueOf(n), dist, String.valueOf(trial),
                String.valueOf(ms),
                String.valueOf(comparisons),
                String.valueOf(arrayAccesses),
                String.valueOf(assignments),
                String.valueOf(allocations)
        );
    }
}
