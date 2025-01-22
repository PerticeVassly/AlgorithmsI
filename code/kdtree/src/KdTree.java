import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.ArrayList;
import java.util.Iterator;


public class KdTree {

    private class Node {
        Node left;
        Node right;

        Point2D data;

        Node(Point2D data) {
            this.data = data;
        }
    }

    private Node root;

    private int size;

    private Point2D closest;

    private double closestDist;

    public KdTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    private Node insertHepler(Point2D p, int depth, Node parent) {
        if (parent == null) {
            return new Node(p);
        }
        double d = 0.0;
        double dNow = 0.0; 
        if (depth % 2 == 0) {
            d = p.x();
            dNow = parent.data.x();
        }
        else {
            d = p.y();
            dNow = parent.data.y();
        }
       
        if (d < dNow) {
            parent.left = insertHepler(p, depth + 1, parent.left);
        }
        else {
            parent.right = insertHepler(p, depth + 1, parent.right);
        }
        return parent;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (contains(p)) return;
        this.root = insertHepler(p, 0, root);
        this.size++;
    }

    private boolean containsHepler(Point2D p, int depth, Node parent) {
        if (parent == null) return false;
        if (parent.data.equals(p)) return true;
        double d = 0.0;
        double dNow = 0.0;
        if (depth % 2 == 0) {
            d = p.x();
            dNow = parent.data.x();
        }
        else {
            d = p.y();
            dNow = parent.data.y();
        }
        if (d < dNow) {
            return containsHepler(p, depth + 1, parent.left);
        }
        else {
            return containsHepler(p, depth + 1, parent.right);
        }
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) {
            return false;
        }
        return containsHepler(p, 0, root);
    }

    private void drawHepler(Node now) {
        if (now == null) return;
        now.data.draw();
        drawHepler(now.left);
        drawHepler(now.right);
    }

    public void draw() {
        drawHepler(root);
    }

    private void rangeHelper(RectHV rect, int depth, Node node, ArrayList<Point2D> ans) {
        if (node == null) return;
        if (node.data.x() >= rect.xmin() &&
                node.data.x() <= rect.xmax() &&
                node.data.y() >= rect.ymin() &&
                node.data.y() <= rect.ymax()) {
            ans.add(node.data);
        }
        double d = 0.0;
        double dMin = 0.0;
        double dMax = 0.0;
        if (depth % 2 == 0) {
            d = node.data.x();
            dMin = rect.xmin();
            dMax = rect.xmax();
        }
        else {
            d = node.data.y();
            dMin = rect.ymin();
            dMax = rect.ymax();
        }
        if (!(d < dMin)) {
            rangeHelper(rect, depth + 1, node.left, ans);
        }
        if (!(d > dMax)) {
            rangeHelper(rect, depth + 1, node.right, ans);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        ArrayList<Point2D> ans = new ArrayList<Point2D>();
        rangeHelper(rect, 0, root, ans);
        return ans;
    }

    private void nearestHelper(Point2D query, RectHV nowIn, Node node,
                               int depth) {
        if (node == null) return;
        double distClosest = closestDist;
        double distNode = query.distanceSquaredTo(node.data);
        if (distNode < distClosest) {
            closest = node.data;
            closestDist = distNode;
        }
        if (distNode == 0) {
            return;
        }
        double xmin = nowIn.xmin();
        double xmax = nowIn.xmax();
        double ymin = nowIn.ymin();
        double ymax = nowIn.ymax();
        double nowx = node.data.x();
        double nowy = node.data.y();
        double qx = query.x();
        double qy = query.y();
        RectHV rRect;
        RectHV lRect;
        boolean inLeft = false;
        if (depth % 2 == 0) {
            rRect = new RectHV(nowx, ymin, xmax, ymax);
            lRect = new RectHV(xmin, ymin, nowx, ymax);
            inLeft = qx < nowx;
        }
        else {
            rRect = new RectHV(xmin, nowy, xmax, ymax);
            lRect = new RectHV(xmin, ymin, xmax, nowy);
            inLeft = qy < nowy;    
        }
        double ld = 0.0;
        double rd = 0.0;
        if (inLeft) {
            ld = lRect.distanceSquaredTo(query);
            if (!(ld > closestDist)) nearestHelper(query, lRect, node.left, depth + 1);
            rd = lRect.distanceSquaredTo(query);
            if (!(rd > closestDist)) nearestHelper(query, rRect, node.right, depth + 1);
        }
        else {
            rd = rRect.distanceSquaredTo(query);
            if (!(rd > closestDist)) nearestHelper(query, rRect, node.right, depth + 1);
            ld = lRect.distanceSquaredTo(query);
            if (!(ld > closestDist)) nearestHelper(query, lRect, node.left, depth + 1);
        }
        return;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (root == null) return null;
        closest = root.data;
        closestDist = p.distanceSquaredTo(root.data);
        nearestHelper(p, new RectHV(0, 0, 1, 1), root, 0);
        return closest;
    }

    public static void main(String[] args) {
        KdTree kdtree = new KdTree();
        kdtree.insert(new Point2D(0.7, 0.2));
        kdtree.insert(new Point2D(0.5, 0.4));
        kdtree.insert(new Point2D(0.2, 0.3));
        kdtree.insert(new Point2D(0.4, 0.7));
        kdtree.insert(new Point2D(0.9, 0.6));
        kdtree.nearest(new Point2D(0.9, 0.6));
        return;
    }
}