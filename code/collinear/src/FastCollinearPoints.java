import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (!checkPointsValid(points)) {
            throw new IllegalArgumentException("points is null");
        }

        segments = new ArrayList<>();
        Point[] pts = points.clone();
        for (int p = 0; p < points.length; p++) {
            Comparator<Point> cmp = points[p].slopeOrder();
            Arrays.sort(pts, cmp);
            int head = 0;
            for (; head < pts.length; head++) {
                int len = 0;
                int tail = head;
                for (; tail < pts.length; tail++) {
                    if (cmp.compare(pts[head], pts[tail]) == 0) {
                        if (pts[tail].compareTo(points[p]) != 0) {
                            len++;
                        }
                    }
                    else {
                        break;
                    }
                }
                Arrays.sort(pts, head, tail);
                if (len >= 3) {
                    if (points[p].compareTo(pts[head]) < 0) {
                        segments.add(new LineSegment(points[p], pts[tail - 1]));
                    }
                }
                head = tail - 1;
            }
        }

    }

    private boolean checkPointsValid(Point[] points) {
        if (points == null) {
            return false;
        }
        for (Point point : points) {
            if (point == null) {
                return false;
            }
        }
        Point[] pts = points.clone();
        Arrays.sort(pts);
        for (int i = 0; i < pts.length; i++) {
            for (int j = i + 1; j < pts.length; j++) {
                if (pts[i].compareTo(pts[j]) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
