import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Builder
{
    public static Client John()
    {
        List<String> shoppingList = Arrays.asList("bread", "chicken wings", "kitkat", "crabs", "milk");
        return new Client("John", shoppingList);
    }
    public static Shop supermarket1()
    {
        List<LineSegment> obstacles = new ArrayList<>();
        LineSegment bakery_seafood = new LineSegment(63.2, 36.2, 88.7, 36.2);
        LineSegment seafood_sweats = new LineSegment(63.2, 45.9, 88.7, 45.9);
        LineSegment makaron_dzavarexen = new LineSegment(52, 25.9, 52, 71.6);
        obstacles.add(seafood_sweats);
        obstacles.add(makaron_dzavarexen);
        obstacles.add(bakery_seafood);
        List<Point> products = new ArrayList<>();
        Point entrance = new Point("entrance", 83.6, 5.9, " 2 ");
        Point cashbox1 = new Point("cashbox1", 33.3, 8.7);
        Point cashbox2 = new Point("cashbox2", 43.7, 8.7);
        Point cashbox3 = new Point("cashbox3", 54.7, 8.7, " 2 ");
        Point cashbox4 = new Point("cashbox4", 66.6, 8.7, " 2 ");
        Point product1 = new Point("milk", 97.4,29.9, " 1 2 ");
        Point product2 = new Point("bread", 79,34.8, " 2 ");
        Point product3 = new Point("crabs", 69.4, 37.9, " 1 3 ");
        Point product4 = new Point("kitkat", 70.3, 47.6, " ");
        Point product5 = new Point("chicken wings", 45.3, 78, " ");
        products.add(entrance);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(cashbox1);
        products.add(cashbox2);
        products.add(cashbox3);
        products.add(cashbox4);
        return new Shop(products, obstacles);
    }
}