import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Navigator
{
    public String shoppingList;
    public int hours;
    public int minutes;
    public int countOfProducts;
    public double overallDistance;
    public List<Point> finalPath = new ArrayList<>();
    public Navigator (String shoppingList)
    {
        this.shoppingList = shoppingList;
    }

    public void supermarket1()
    {
        Shop supermarket1 = Builder.supermarket1();
        //Client John = Builder.John();
        List<Point> products = supermarket1.products;
        String shoppingItems = shoppingList;
        List<Point> JohnsProducts = new ArrayList<>();
        for (Point product : products)
        {
            if (shoppingItems.contains(product.name))
                JohnsProducts.add(product);
        }
        countOfProducts = JohnsProducts.size();
        Point start = new Point();
        Point end = new Point();
        Point next = new Point();
        for (Point p : products)
            if (p.name.equals("entrance"))
                start = p;
        List<Point> JohnsRoute = new ArrayList<>();
        JohnsRoute.add(start);
        List<double[]> distances = supermarket1.distances;
        double min = Double.MAX_VALUE;
        while (!JohnsProducts.isEmpty())
        {
            for (Point point : JohnsProducts)
            {
                end = point;
                for (double[] distance : distances)
                {
                    if (distance[0] == start.innerIndex && distance[1] == end.innerIndex
                            || distance[1] == start.innerIndex && distance[0] == end.innerIndex)
                        if (distance[2] < min)
                        {
                            min = distance[2];
                            next = end;
                        }
                }
            }
            JohnsRoute.add(next);
            start = next;
            min = Double.MAX_VALUE;
            JohnsProducts.remove(next);
        }
        start = JohnsRoute.getLast();
        List<Point> cashboxes = new ArrayList<>();
        for (Point p : products)
            if (p.name.contains("cashbox"))
                cashboxes.add(p);
        for (Point cashbox : cashboxes)
        {
            end = cashbox;
            for (double[] distance : distances)
            {
                if (distance[0] == start.innerIndex && distance[1] == end.innerIndex
                        || distance[1] == start.innerIndex && distance[0] == end.innerIndex)
                    if (distance[2] < min)
                    {
                        min = distance[2];
                        next = end;
                    }
            }
        }
        JohnsRoute.add(next);
        overallDistance = 0;
        System.out.println(JohnsRoute + "\nEach distance and its path:");
        for (int i = 0; i < JohnsRoute.size() - 1; i++)
        {
            start = JohnsRoute.get(i);
            next = JohnsRoute.get(i + 1);
            for (int k = 0; k < distances.size(); k++)
            {
                if (distances.get(k)[0] == start.innerIndex && distances.get(k)[1] == next.innerIndex)
                {
                    overallDistance += distances.get(k)[2];
                    System.out.println(distances.get(k)[2] + ", " + supermarket1.listOfPaths.get(k).points);
                    finalPath.addAll(supermarket1.listOfPaths.get(k).points);
                }
                else if (distances.get(k)[1] == start.innerIndex && distances.get(k)[0] == next.innerIndex)
                {
                    overallDistance += distances.get(k)[2];
                    System.out.println(distances.get(k)[2] + ", " + supermarket1.listOfPaths.get(k).points.reversed());
                    finalPath.addAll(supermarket1.listOfPaths.get(k).points.reversed());
                }
            }
            finalPath.removeLast();
        }
        finalPath.add(next);
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(new File("points.json"), finalPath);
            System.out.println("Points saved to points.json!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        overallDistance = overallDistance / 10;
        double time = (overallDistance / .5 + countOfProducts * 10) / 3600;
        hours = (int) time;
        minutes = (int) ((time - hours) * 60);
        minutes = minutes < 1? 1 : minutes;
        overallDistance = (int) overallDistance;
        System.out.printf("Overall distance: %.2f meters \n", overallDistance);
        System.out.printf("Expected time: %d hours, %d minutes\n", hours, minutes);
    }
}
