package algorithms;

public class Result {
    public final int maxSum;
    public final int left;
    public final int right;

    public Result(int maxSum, int left, int right) {
        this.maxSum = maxSum;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Result{sum=" + maxSum + ", L=" + left + ", R=" + right + "}";
    }
}
