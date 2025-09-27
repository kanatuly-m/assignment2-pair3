package algorithms;

import metrics.PerformanceTracker;

public class Kadane {
    public static Result kadane(int[] a, PerformanceTracker t) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        t.startTimer();

        int bestSum = a[0]; t.incArrayAccesses();
        int curSum  = a[0]; t.incArrayAccesses();
        int bestL = 0, bestR = 0, curL = 0;

        for (int i = 1; i < a.length; i++) {
            t.incComparisons();
            t.incArrayAccesses();
            int x = a[i];

            // если текущая сумма уходит в минус — начинаем новый отрезок
            t.incComparisons();
            if (curSum + x < x) {
                curSum = x;      t.incAssignments();
                curL = i;        t.incAssignments();
            } else {
                curSum += x;     t.incAssignments();
            }

            // обновляем лучший ответ
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
