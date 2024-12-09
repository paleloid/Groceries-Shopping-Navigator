import java.util.*;

public class Shop implements EuclideanDistance
{
    public List<Point> products;
    public List<LineSegment> obstacles;
    public List<double[]> distances = new ArrayList<double[]>();
    public List<Path> listOfPaths = new ArrayList<Path>();
    public Shop(List<Point> products, List<LineSegment> obstacles)
    {
        this.products = products;
        this.obstacles = obstacles;
        calculateDistances();
    }
    public void calculateDistances()
    {
        Point start;
        Point end;
        boolean right = true, up = true;
        int k = 0;
        for (int i = 0; i < products.size(); i++)
        {
            products.get(i).innerIndex = i;
            for (int j = i + 1; j < products.size(); j++)
            {
                start = products.get(i);
                listOfPaths.add(new Path(start));
                end = products.get(j);
                if (Point.sameSection(start, end))
                    listOfPaths.get(k).points.add(end);
                else
                {
                    right = end.x >= start.x;
                    up = end.y >= start.y;
                    LineSegment.sort(obstacles, right, up);
                    LineSegment segment = new LineSegment(start, end);
                    for (LineSegment obstacle : obstacles)
                    {
                        Point middle = obstacle.middle();
                        if (obstacle.isVertical() && LineSegment.inRangeX(middle, start, end)
                                || !obstacle.isVertical() && LineSegment.inRangeY(middle, start, end))
                            if (LineSegment.doLineSegmentsCross(segment, obstacle))
                            {
                                start = LineSegment.whichExit(start, end, obstacle, right, up);
                                listOfPaths.get(k).points.add(start);
                                right = end.x >= start.x;
                                up = end.y >= start.y;
                            }
                    }
                    listOfPaths.get(k).points.add(end);
                }
                distances.add(new double[] {i, j, listOfPaths.get(k).length()});
                k++;
            }
        }
    }
    public List<double[]> getDistances()
    {
        return distances;
    }
    public static double finalDistance(List<Path> listOfPaths)
    {
        double sum = 0;
        for (Path path : listOfPaths)
            sum += path.length();
        return sum;
    }
    @Override
    public String toString() {
        return "Shop{" +
                "products=" + products +
                ", obstacles=" + obstacles +
                ", distances=" + distances +
                ", listOfPaths=" + listOfPaths +
                '}';
    }
}