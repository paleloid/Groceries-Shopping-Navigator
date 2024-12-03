import java.util.Comparator;
import java.util.List;

public class LineSegment implements EuclideanDistance
{
    public int index;
    public double x1, y1, x2, y2;
    public Point p1, q1;
    private boolean isVertical;
    public LineSegment(Point p1, Point q1)
    {
        this.p1 = p1;
        this.q1 = q1;
    }
    public LineSegment(int index, Point p1, Point q1)
    {
        this.index = index;
        this.p1 = p1;
        this.q1 = q1;
    }
    public LineSegment(double x1, double y1, double x2, double y2)
    {
        if (x1 == x2)
        {
            this.isVertical = true;
            this.x2 = this.x1 = x1;
            this.y1 = Math.min(y1, y2); // lower
            this.y2 = Math.max(y1, y2); // upper
        }
        else
        {
            this.isVertical = false;
            this.y1 = this.y2 = y1;
            this.x1 = Math.min(x1, x2); // left
            this.x2 = Math.max(x1, x2); // right
        }
        this.p1 = new Point(x1, y1);
        this.q1 = new Point(x2, y2);
    }
    public LineSegment(int index, double x1, double y1, double x2, double y2)
    {
        this.index = index;
        if (x1 == x2)
        {
            this.isVertical = true;
            this.x2 = this.x1 = x1;
            this.y1 = Math.min(y1, y2); // lower
            this.y2 = Math.max(y1, y2); // upper
        }
        else
        {
            this.isVertical = false;
            this.y1 = this.y2 = y1;
            this.x1 = Math.min(x1, x2); // left
            this.x2 = Math.max(x1, x2); // right
        }
        this.p1 = new Point(x1, y1);
        this.q1 = new Point(x2, y2);
    }
    public boolean isVertical()
    {
        return isVertical;
    }
    public Point middle()
    {
        return new Point((this.x1 + this.x2) / 2, (this.y1 + this.y2) / 2);
    }
    public Point end1()
    {
        return new Point(isVertical? x1: x1 - 1, isVertical? y1 - 1: y1);
    }
    public Point end2()
    {
        return new Point(isVertical? x1: x2 + 1, isVertical? y2 + 1: y1);
    }
    public double length()
    {
         return distance(x1, y1, x2, y2);
    }
    public static boolean inRangeX(Point r, Point p, Point q)
    {
        return r.x <= Math.max(p.x, q.x) && r.x >= Math.min(p.x, q.x);
    }
    public static boolean inRangeY(Point r, Point p, Point q)
    {
        return r.y <= Math.max(p.y, q.y) && r.y >= Math.min(p.y, q.y);
    }
    public static Point whichExit(Point start, Point end, LineSegment obstacle, boolean right, boolean up)
    {
        double startToMid, endToMid;
        Point end1 = obstacle.end1(), end2 = obstacle.end2();
        boolean vertical = obstacle.isVertical();
        startToMid = vertical? start.y - obstacle.middle().y: start.x - obstacle.middle().x;
        endToMid = vertical? end.y - obstacle.middle().y: end.x - obstacle.middle().x;
        if (startToMid >= 0 && endToMid >= 0 || startToMid == - endToMid) return end2;
        if (startToMid <= 0 && endToMid <= 0) return end1;
        if (Math.abs(startToMid) > Math.abs(endToMid))
        {
            if (vertical? up: right) return end1;
            else return end2;
        }
        else
        if (vertical? up: right) return end2;
        else return end1;
    }
    public static void sort(List<LineSegment> obstacles, boolean right, boolean up)
    {
        String direction;
        if (!right && !up)
            direction = "LeftDown";
        else
            direction = right? (up? "RightUp": "RightDown") : "LeftUp";
        switch (direction)
        {
            case "LeftUp":
                obstacles.sort(new Comparator<LineSegment>() {
                    @Override
                    public int compare(LineSegment l1, LineSegment l2) {
                        // Compare x-coordinates first
                        int compareX = Double.compare(l2.middle().x, l1.middle().x);
                        if (compareX != 0)
                            return compareX;
                        // If x-coordinates are equal, compare y-coordinates
                        return (int) Double.compare(l1.middle().y, l2.middle().y);
                    }
                }); break;
            case "LeftDown":
                obstacles.sort(new Comparator<LineSegment>() {
                    @Override
                    public int compare(LineSegment l1, LineSegment l2) {
                        // Compare x-coordinates first
                        int compareX = Double.compare(l2.middle().x, l1.middle().x);
                        if (compareX != 0)
                            return compareX;
                        // If x-coordinates are equal, compare y-coordinates
                        return (int) Double.compare(l2.middle().y, l1.middle().y);
                    }
                }); break;
            case "RightUp":
                obstacles.sort(new Comparator<LineSegment>() {
                    @Override
                    public int compare(LineSegment l1, LineSegment l2) {
                        // Compare x-coordinates first
                        int compareX = Double.compare(l1.middle().x, l2.middle().x);
                        if (compareX != 0)
                            return compareX;
                        // If x-coordinates are equal, compare y-coordinates
                        return (int) Double.compare(l1.middle().y, l2.middle().y);
                    }
                }); break;
            case "RightDown":
                obstacles.sort(new Comparator<LineSegment>() {
                    @Override
                    public int compare(LineSegment l1, LineSegment l2) {
                        // Compare x-coordinates first
                        int compareX = Double.compare(l1.middle().x, l2.middle().x);
                        if (compareX != 0)
                            return compareX;
                        // If x-coordinates are equal, compare y-coordinates
                        return (int) Double.compare(l2.middle().y, l1.middle().y);
                    }
                }); break;
            default: break;
        }
    }
    // https://www.youtube.com/watch?v=wCR48FqkI4w
    static boolean doIntersect(Point p1, Point q1, Point p2, Point q2)
    {
        // Find orientations
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);
        // General case
        if (o1 != o2 && o3 != o4)
            return true;
        // Special Cases
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        return o4 == 0 && onSegment(p2, q1, q2);
    }
    // Function to find the orientation of three points
    static int orientation(Point p, Point q, Point r) {
        double val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0; // Collinear
        return (val > 0) ? 1 : 2; // Clockwise or counterclockwise
    }

    // Function to check if a point lies on a line segment, when they are already collinear
    static boolean onSegment(Point p, Point q, Point r) {
        return q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y);
    }

    // Function to check if two line segments cross
    static boolean doLineSegmentsCross(LineSegment l1, LineSegment l2) {
        return doIntersect(l1.p1, l1.q1, l2.p1, l2.q1);
    }

    @Override
    public String toString() {
        return "LineSegment{" +
                "index=" + index +
                ", x1=" + x1 +
                ", y1=" + y1 +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", p1=" + p1 +
                ", q1=" + q1 +
                ", isVertical=" + isVertical() +
                '}';
    }
}