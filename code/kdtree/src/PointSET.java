import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class PointSET {

    private TreeSet<Point2D> data;

    public PointSET() {
        data = new TreeSet<>();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (contains(p)) return;
        data.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return data.contains(p);
    }

    public void draw() {
        for (Point2D p : data) {
            StdDraw.point(p.x(), p.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        ArrayList<Point2D> ans = new ArrayList<>();
        for (Point2D p : data) {
            if (p.x() >= rect.xmin()
                    && p.x() <= rect.xmax()
                    && p.y() >= rect.ymin()
                    && p.y() <= rect.ymax()) {
                ans.add(new Point2D(p.x(), p.y()));
            }
        }
        return ans;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            return null;
        }
        Point2D ans = null;
        double minLen = Double.MAX_VALUE;
        for (Point2D tmp : data) {
            if (tmp.distanceSquaredTo(p) <= minLen) {
                minLen = tmp.distanceSquaredTo(p);
                ans = tmp;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        PointSET pset = new PointSET();
        pset.insert(new Point2D(0, 0));
        pset.insert(new Point2D(0, 0.4));
        pset.insert(new Point2D(0.4, 0.4));
        pset.insert(new Point2D(0.4, 0));
        pset.insert(new Point2D(0.2, 0.2));
        assert !pset.isEmpty();
        assert pset.size() == 5;
        assert pset.contains(new Point2D(0, 0));
        Iterator<Point2D> it = pset.range(new RectHV(0.1, 0.1, 0.3, 0.3)).iterator();
        Point2D p = it.next();
        assert p.equals(new Point2D(0.2, 0.2));
        return;
    }
}
