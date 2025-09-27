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
                candidate = x;
                count = 1;
                t.incAssignments();
            } else if (x == candidate) {
                count++;
                t.incAssignments();
            } else {
                count--;
                t.incAssignments();
            }
            t.incComparisons();
        }

        // шаг 2: проверка кандидата
        int freq = 0;
        for (int x : a) {
            t.incArrayAccesses();
            if (x == candidate) {
                freq++;
                t.incAssignments();
            }
        }

        t.stopTimer();

        if (freq > a.length / 2) {
            return candidate;
        } else {
            return null; // нет большинства
        }
    }
}