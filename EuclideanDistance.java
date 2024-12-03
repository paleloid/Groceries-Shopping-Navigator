public interface EuclideanDistance
{
    default double distance(Point p1, Point q1)
    {
        return Math.sqrt(Math.pow(p1.x - q1.x, 2) + Math.pow(p1.y - q1.y, 2));
    }
    default double distance(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}