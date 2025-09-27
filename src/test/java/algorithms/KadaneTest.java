package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KadaneTest {

    @Test
    void singleElement() {
        int[] a = {5};
        Result r = Kadane.kadane(a, new PerformanceTracker());
        assertEquals(5, r.maxSum);
        assertEquals(0, r.left);
        assertEquals(0, r.right);
    }

    @Test
    void allNegative() {
        int[] a = {-3, -5, -1};
        Result r = Kadane.kadane(a, new PerformanceTracker());
        assertEquals(-1, r.maxSum);
        assertEquals(2, r.left);
        assertEquals(2, r.right);
    }

    @Test
    void typicalCase() {
        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4}; // ответ: 6 на [3..6]
        Result r = Kadane.kadane(a, new PerformanceTracker());
        assertEquals(6, r.maxSum);
        assertEquals(3, r.left);
        assertEquals(6, r.right);
    }
}
