package algorithms;

import metrics.PerformanceTracker;

public class MajorityVote {
    public static Integer findMajority(int[] a, PerformanceTracker t) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        t.startTimer();

        // шаг 1: выбор кандидата
        Integer candidate = null;
        int count = 0;

        for (int x : a) {
            t.incArrayAccesses();
            if (count == 0) {
                candidate = x;  t.incAssignments();
                count = 1;      t.incAssignments();
            } else if (x == candidate) {
                count++;        t.incAssignments();
            } else {
                count--;        t.incAssignments();
            }
        }

        // шаг 2: проверка, что кандидат действительно > n/2
        int freq = 0;
        for (int x : a) {
            t.incArrayAccesses();
            if (x == candidate) {
                freq++;         t.incAssignments();
            }
        }

        t.stopTimer();
        return (freq > a.length / 2) ? candidate : null;
    }
}
