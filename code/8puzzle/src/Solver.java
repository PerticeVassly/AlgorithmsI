import java.util.ArrayList;
import java.util.HashSet;
import edu.princeton.cs.algs4.MinPQ;
import java.util.Collections;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private Board firstStep;
    private MinPQ<Node> pq = new MinPQ<Node>();
    private boolean isSolvable = false;
    private ArrayList<Board> solution = new ArrayList<Board>();

    class Node implements Comparable<Node> {
       
        private Board board;
        private Node parent;
        private int moves;
        private int priority;

        public Node(Board board, Node parent, int moves) {
            this.board = board;
            this.parent = parent;
            this.moves = moves;
            this.priority = board.hamming() + moves;
        } 
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        firstStep = initial;
        Node lastNode = solve();
        if (lastNode != null) {
            isSolvable = true;
        }
        if (isSolvable) {
            // add all the steps to the solution
            ArrayList<Board> solution = new ArrayList<Board>();
            Node current = lastNode;
            while (current != null) {
                solution.add(current.board);
                current = current.parent;
            }
            Collections.reverse(solution);
            this.solution = solution;
        } 
    }

    private Node solve() {
        // every move just change one tile if it change to the right place then priority is - 1 and + 1 maintain.
        HashSet<Board> visited = new HashSet<Board>();
        pq.insert(new Node(firstStep, null, 0));
        while(!pq.isEmpty()) {
            Node toRemove = pq.delMin();
            if (toRemove.board.isGoal()) {
                return toRemove;
            } else {
                for (Board neighbor : toRemove.board.neighbors()) {
                   //add all unvisited neighbors to the priority queue
                    if (!visited.contains(neighbor)) {
                        pq.insert(new Node(neighbor, toRemove, toRemove.moves + 1));
                        visited.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable) {
            return solution.size() - 1;
        } else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable) {
            return null;
        }
        return this.solution;
    }

    // test client (see below) 
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
            StdOut.println(board);
        }
    }
}