import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size; // start from 1 to n
    private boolean[][] sites;
    private int openedNum = 0;
    private WeightedQuickUnionUF wquf;
    private WeightedQuickUnionUF novirtualwquf;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid parameter n <= 0.");
        }

        size = n;

        sites = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sites[j][i] = false;
            }
        }

        wquf = new WeightedQuickUnionUF(n * n + 2);
        novirtualwquf = new WeightedQuickUnionUF(n * n + 2);
        // here 0 means the head and n * n means the bottom ; n*n + 1 means the the
        // bottom

        /*
         * n = 3;
         * 0
         * 1 2 3
         * 4 5 6
         * 7 8 9
         * 10
         */

    }

    public void open(int row, int col) {
        if (isOutOfbound(row, col)) {
            throw new IllegalArgumentException("Out of size.");
        }

        if (isOpen(row, col)) {
            return;
        }

        sites[row][col] = true;

        connectArround(row, col);

        openedNum++;
    }

    private void connectArround(int row, int col) {

        int index = (row - 1) * size + col;

        if (!isOutOfbound(row + 1, col) && isOpen(row + 1, col)) {
            wquf.union(index + size, index);
            novirtualwquf.union(index + size, index);
        }
        if (!isOutOfbound(row - 1, col) && isOpen(row - 1, col)) {
            wquf.union(index - size, index);
            novirtualwquf.union(index - size, index);
        }
        if (!isOutOfbound(row, col + 1) && isOpen(row, col + 1)) {
            wquf.union(index, index + 1);
            novirtualwquf.union(index, index + 1);
        }
        if (!isOutOfbound(row, col - 1) && isOpen(row, col - 1)) {
            wquf.union(index, index - 1);
            novirtualwquf.union(index, index - 1);
        }

        if (row == 1) {
            wquf.union(index, 0);
            novirtualwquf.union(index, 0);
        }
        if (row == size) {
            wquf.union(index, size * size + 1);
        }
    }

    private boolean isOutOfbound(int row, int col) {
        return row > size || col > size || row < 1 || col < 1;
    }

    public boolean isOpen(int row, int col) {
        if (isOutOfbound(row, col)) {
            throw new IllegalArgumentException();
        }
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        if (isOutOfbound(row, col)) {
            throw new IllegalArgumentException();
        }
        return novirtualwquf.find((row - 1) * size + col) == novirtualwquf.find(0);
    }

    public int numberOfOpenSites() {
        return openedNum;
    }

    public boolean percolates() {
        return wquf.find(0) == wquf.find(size * size + 1);
    }
}