import java.util.ArrayList;
import java.util.List;

public class Board {

    private int dimension;
    private int[][] titles; 
    private int hamming;
    private int manhattan;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        titles = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                titles[i][j] = tiles[i][j];
            }
        }
        this.dimension = tiles.length;
        this.hamming = calHamming();
        this.manhattan = calManhattan();
        return;
    }
                                           
    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dimension + "\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sb.append(" " + titles[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return this.dimension;
    }

    // number of tiles out of place
    private int calHamming() {
        int hammingCount = 0;
        int goal = 1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (titles[i][j] != 0 && titles[i][j] != goal) {
                    hammingCount++;
                }
                goal++;
            }
        }
        return hammingCount;
    }

    public int hamming() {
        return this.hamming;
    }

    // sum of Manhattan distances between tiles and goal
    private int calManhattan() {
        int manhattanCount = 0;
        int goal = 1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (titles[i][j] != 0 && titles[i][j] != goal) {
                    int row = (titles[i][j] - 1) / dimension;
                    int col = (titles[i][j] - 1) % dimension;
                    manhattanCount += Math.abs(row - i) + Math.abs(col - j);
                }
                goal++;
            }
        }
        return manhattanCount;
    }

    public int manhattan() {
        return this.manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int goal = 1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (titles[i][j] != 0 && titles[i][j] != goal) {
                    return false;
                }
                goal++;
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (y == this) {
            return true;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board that = (Board) y;
        if (that.dimension != this.dimension) {
            return false;
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (that.titles[i][j] != this.titles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighborsList = new ArrayList<>();
        int row = 0, col = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (titles[i][j] == 0) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < dimension && newCol >= 0 && newCol < dimension) {
                int[][] tmp = copyData(titles);
                tmp[row][col] = tmp[newRow][newCol];
                tmp[newRow][newCol] = 0;
                neighborsList.add(new Board(tmp));
            }
        }
        return neighborsList;
    }

    private int[][] copyData(int[][] data) {
        int[][] copy = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            System.arraycopy(data[i], 0, copy[i], 0, dimension);
        }
        return copy;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] tmp = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tmp[i][j] = titles[i][j];
            }
        }
        if (tmp[0][0] != 0 && tmp[0][1] != 0) {
            int t = tmp[0][0];
            tmp[0][0] = tmp[0][1];
            tmp[0][1] = t;
        } else {
            int t = tmp[1][0];
            tmp[1][0] = tmp[1][1];
            tmp[1][1] = t;
        }
        return new Board(tmp);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int data[][] = {{1, 2, 3}, {4, 5, 6}, {8, 7, 0}};
        Board b1 = new Board(data);
        System.out.println("test toString");
        System.out.println(b1.toString());

        System.out.println("test dimension");
        System.out.println(b1.dimension());

        System.out.println("test hamming");
        System.out.println(b1.calHamming());

        System.out.println("test manhattan");
        System.out.println(b1.calManhattan());

        System.out.println("test isGoal");
        System.out.println(b1.isGoal());

        System.out.println("test equals");
        int newData[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board b2 = new Board(newData);
        System.out.println(b1.equals(b2));

        System.out.println("test neighbors");
        for (Board b : b1.neighbors()) {
            System.out.println(b.toString());
        }

        System.out.println("test twin");
        System.out.println(b1.twin().toString());
    }
}