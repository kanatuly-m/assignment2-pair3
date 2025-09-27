package algorithms;

import metrics.PerformanceTracker;

public class  Kadane {
    public static Result kadane(int[] a, PerformanceTracker t) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        t.startTimer();

        int bestSum = a[0]; t.incArrayAccesses();
        int curSum  = a[0]; t.incArrayAccesses();
        int bestL = 0, bestR = 0, curL = 0;

        for (int i = 1; i < a.length; i++) {
            t.incComparisons();                  // i < a.length
            int x = a[i]; t.incArrayAccesses();  // чтение a[i]

            // начать новый подмассив или продолжить текущий
            t.incComparisons();
            if (curSum + x < x) {
                curSum = x; t.incAssignments();
                curL = i;  t.incAssignments();
            } else {
                curSum = curSum + x; t.incAssignments();
            }

            // обновить лучший ответ
            t.incComparisons();
            if (curSum > bestSum) {
                bestSum = curSum; t.incAssignments();
                bestL = curL;     t.incAssignments();
                bestR = i;        t.incAssignments();
            }
        }

        t.stopTimer();
        return new Result(bestSum, bestL, bestR);
    }
}
