import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int n = 0;
        while (!StdIn.isEmpty()) {
            n++;
            String s = StdIn.readString();
            if (n > k) {
                if (StdRandom.bernoulli(((double) k) / n)) {
                    rq.dequeue();
                    rq.enqueue(s);
                }
            }
            else {
                rq.enqueue(s);
            }
        }

        for (int i = 0; i < k; i++) {
            System.out.println(rq.dequeue());
        }
    }
}
