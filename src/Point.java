public class Point
{
    public double x, y;
    public int innerIndex;
    public String name, sectionIndexes;
    public Point(){}
    public Point(String name, double x, double y)
    {
        this.name = name.toLowerCase();
        this.x = x;
        this.y = y;
    }
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public Point(String name, double x, double y, String sectionIndexes)
    {
        this.name = name.toLowerCase();
        this.x = x;
        this.y = y;
        this.sectionIndexes = sectionIndexes;
    }
    public Point(double x, double y, String sectionIndexes)
    {
        this.x = x;
        this.y = y;
        this.sectionIndexes = sectionIndexes;
    }
    public static boolean sameSection(Point p1, Point q1)
    {
        if (p1.sectionIndexes == null || p1.sectionIndexes.isEmpty() || q1.sectionIndexes == null || q1.sectionIndexes.isEmpty())
            return false;
        String indexes1 = p1.sectionIndexes;
        String indexes2 = q1.sectionIndexes;
        int index1, index2;
        while (indexes1.length() > 2)
        {
            index1 = indexes1.indexOf(" ");
            index2 = indexes1.indexOf(" ", 1);
            if (indexes2.contains(indexes1.substring(index1, index2 + 1))) return true;
            indexes1 = indexes1.substring(index2);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", name=" + name +
                ", sectionIndexes='" + sectionIndexes + '\'' +
                '}';
    }
}