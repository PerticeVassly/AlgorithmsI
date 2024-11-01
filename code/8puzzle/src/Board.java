import java.util.Iterator;

public class Board {

    int size;
    private int[][] data; 

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        data = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                data[i][j] = tiles[i][j];
            }
        }
        this.size = tiles.length;
        return;
    }
                                           
    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j == size - 1) {
                    sb.append(data[i][j]);
                } else {
                    sb.append(data[i][j] + " ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return size;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        int goal = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (data[i][j] != 0 && data[i][j] != goal) {
                    count++;
                }
                goal++;
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int count = 0;
        int goal = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (data[i][j] != 0 && data[i][j] != goal) {
                    int row = (data[i][j] - 1) / size;
                    int col = (data[i][j] - 1) % size;
                    count += Math.abs(row - i) + Math.abs(col - j);
                }
                goal++;
            }
        }
        return count;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int goal = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (data[i][j] != goal) {
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
        if (that.size != this.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (that.data[i][j] != this.data[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new NeighborsList();
    }

    class NeighborsList implements Iterable<Board> {

        Board[] neighbors = new Board[4];
        int num = 0;

        NeighborsList() {
            int row = 0;
            int col = 0;
            int[][] tmp = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    tmp[i][j] = data[i][j];
                    if (data[i][j] == 0) {
                        row = i;
                        col = j;
                    }
                }
            }

            //try to exchange with left right up down
            if (row > 0) {
                tmp[row][col] = tmp[row - 1][col];
                tmp[row - 1][col] = 0;
                neighbors[num++] = new Board(tmp);
                tmp[row - 1][col] = tmp[row][col];
                tmp[row][col] = 0;
            }
            if (row < size - 1) {
                tmp[row][col] = tmp[row + 1][col];
                tmp[row + 1][col] = 0;
                neighbors[num++] = new Board(tmp);
                tmp[row + 1][col] = tmp[row][col];
                tmp[row][col] = 0;
            }
            if (col > 0) {
                tmp[row][col] = tmp[row][col - 1];
                tmp[row][col - 1] = 0;
                neighbors[num++] = new Board(tmp);
                tmp[row][col - 1] = tmp[row][col];
                tmp[row][col] = 0;
            }
            if (col < size - 1) {
                tmp[row][col] = tmp[row][col + 1];
                tmp[row][col + 1] = 0;
                neighbors[num++] = new Board(tmp);
                tmp[row][col + 1] = tmp[row][col];
                tmp[row][col] = 0;
            }
        }

        @Override
        public Iterator<Board> iterator() {
            return new NeighborsIterator();
        }

        class NeighborsIterator implements Iterator<Board> {

            int current = 0;

            @Override
            public boolean hasNext() {
                return current < num;
            }

            @Override
            public Board next() {
                return neighbors[current++];
            }
        }
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] tmp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp[i][j] = data[i][j];
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
        System.out.println(b1.hamming());

        System.out.println("test manhattan");
        System.out.println(b1.manhattan());

        System.out.println("test isGoal");
        System.out.println(b1.isGoal());

        System.out.println("test equals");
        int new_data[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board b2 = new Board(new_data);
        System.out.println(b1.equals(b2));

        System.out.println("test neighbors");
        for (Board b : b1.neighbors()) {
            System.out.println(b.toString());
        }

        System.out.println("test twin");
        System.out.println(b1.twin().toString());
    }
}