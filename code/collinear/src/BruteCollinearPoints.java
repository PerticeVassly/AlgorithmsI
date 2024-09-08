import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        if (!checkPointsValid(points)) {
            throw new IllegalArgumentException("points is null");
        }

        segments = new ArrayList<>();
        for (int p = 0; p < points.length; p++) {
            for (int q = p + 1; q < points.length; q++) {
                for (int r = q + 1; r < points.length; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].slopeOrder().compare(points[q], points[r]) == 0 &&
                                points[p].slopeOrder().compare(points[r], points[s]) == 0) {
                            Point[] fourPoints = new Point[] {
                                    points[p], points[q], points[r], points[s]
                            };
                            Arrays.sort(fourPoints);
                            segments.add(new LineSegment(fourPoints[0], fourPoints[3]));
                        }
                    }
                }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
