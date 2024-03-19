import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double[] ans;
    private int trials = 0;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n <= 0 or trails <=0.");
        }

        ans = new double[trials + 1];

        this.trials = trials;

        for (int t = 1; t <= trials; t++) {
            // get a threhold
            Percolation percolation = new Percolation(n);
            while (percolation.percolates() != true) {
                // choose a random unopen site
                // open i_final, j_final;
                int i_final = StdRandom.uniformInt(1, n + 1);
                int j_final = StdRandom.uniformInt(1, n + 1);
                percolation.open(j_final, i_final);
            }
            // now percolates:

            ans[t] = (double) percolation.numberOfOpenSites() / (n * n);
        }

        // get a ans[];
    }

    public double mean() {
        return StdStats.mean(ans, 1, ans.length);
    }

    public double stddev() {
        return StdStats.stddev(ans, 1, ans.length);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(trials);

    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid parameters!");
            return;
        }

        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        // output
        System.out.println("mean\t" + "= " + stats.mean());
        System.out.println("stddev\t" + "= " + stats.stddev());
        System.out.println(
                "95% confidence interval\t" + "= [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
