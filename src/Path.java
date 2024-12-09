import java.util.ArrayList;
import java.util.List;

public class Path implements EuclideanDistance
{
    public List<Point> points = new ArrayList<Point>();
    //public List<Point> pointsReversed = points.reversed();
    public Path(){}
    public Path(Point p)
    {
        this.points.add(p);
    }
    public double length()
    {
        double length = 0;
        Point thisPoint, nextPoint;
        for (int i = 0; i < points.size() - 1; i++)
        {
            thisPoint = points.get(i);
            nextPoint = points.get(i + 1);
            length += distance(thisPoint, nextPoint);
        }
        return length;
    }
    @Override
    public String toString() {
        return "Path{" +
                "points=" + points +
                '}';
    }
}