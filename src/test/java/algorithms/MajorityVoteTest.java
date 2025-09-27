package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MajorityVoteTest {

    @Test
    void majorityExists() {
        int[] a = {2, 2, 1, 2, 3, 2, 2};
        Integer result = MajorityVote.findMajority(a, new PerformanceTracker());
        assertEquals(2, result);
    }

    @Test
    void noMajority() {
        int[] a = {1, 2, 3, 4};
        Integer result = MajorityVote.findMajority(a, new PerformanceTracker());
        assertNull(result);
    }

    @Test
    void singleElement() {
        int[] a = {7};
        Integer result = MajorityVote.findMajority(a, new PerformanceTracker());
        assertEquals(7, result);
    }
}