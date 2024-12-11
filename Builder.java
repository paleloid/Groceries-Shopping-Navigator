import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Builder extends JFrame implements ActionListener
{
    JLabel forShoppingList = new JLabel("Enter your shopping products");
    JTextField Items = new JTextField(100);
    JButton goButton = new JButton("Go");
    public Builder() throws HeadlessException
    {
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(1000, 500);
        add(forShoppingList);
        add(Items);
        add(goButton);
        goButton.addActionListener(this);
    }

    @Override
    public void actionPerformed (ActionEvent e)
    {
        Client John = new Client(Items.getText());
        Navigator navigation = new Navigator(John.shoppingItems);
        navigation.supermarket1();
        JLabel distance = new JLabel("Distance estimated: " + navigation.overallDistance + "m. ");
        add(distance);
        JLabel time = new JLabel("Time estimation: " + navigation.hours + " hours, " +
                navigation.minutes + " minutes");
        add(time);
    }

    /*public static Client John ()
    {
        List<String> shoppingList = Arrays.asList("water", "rice", "cracker", "pancakes", "sujuk", "grain", "cigarettes");
        return new Client("John", shoppingList);
    }*/
    public static Shop supermarket1()
    {
        List<LineSegment> obstacles = new ArrayList<>();
        LineSegment bakery_seafood = new LineSegment(63.2, 36.2, 88.7, 36.2);
        LineSegment seafood_sweats = new LineSegment(63.2, 45.9, 88.7, 45.9);
        LineSegment groats_snack = new LineSegment(52, 25.9, 52, 71.6);
        LineSegment sweats_oil = new LineSegment(63.2, 57, 88.7, 57);
        LineSegment oil_meat = new LineSegment(63.2, 67.3, 88.7, 67.3);
        LineSegment semicooked_legumes = new LineSegment(37.8, 25.9, 37.8, 71.6);
        LineSegment caned_semicooked = new LineSegment(24.8, 25.9, 24.8, 71.6);
        LineSegment drinks_canned = new LineSegment(11.6, 25.9, 11.6, 71.6);
        LineSegment cigarettes_drinks = new LineSegment(4.1, 14.7, 20.6, 14.7);
        obstacles.add(seafood_sweats);
        obstacles.add(groats_snack);
        obstacles.add(bakery_seafood);
        obstacles.add(sweats_oil);
        obstacles.add(oil_meat);
        obstacles.add(semicooked_legumes);
        obstacles.add(caned_semicooked);
        obstacles.add(drinks_canned);
        obstacles.add(cigarettes_drinks);

        List<Point> products = new ArrayList<>();
        Point entrance = new Point("entrance", 83.6, 5.9, " 0 2 ");
        Point cashbox1 = new Point("cashbox1", 33.3, 8.7, " 0 9 ");
        Point cashbox2 = new Point("cashbox2", 43.7, 8.7, " 0 8 ");
        Point cashbox3 = new Point("cashbox3", 54.7, 8.7, " 0 2 7 ");
        Point cashbox4 = new Point("cashbox4", 66.6, 8.7, " 0 2 7 ");
        Point product1 = new Point("milk", 97.4,29.9, " 1 2 ");
        Point product2 = new Point("bread", 79,34.8, " 2 ");
        Point product3 = new Point("crabs", 69.4, 37.9, " 3 ");
        Point product4 = new Point("kitkat", 70.3, 47.6, " 4 ");
        Point product5 = new Point("chicken wings", 45.3, 78, " 6 8 ");
        Point product6 = new Point("Waffles", 78.4, 55.7, " 4 ");
        Point product7 = new Point("Fish", 77.4, 44.7, " 3 ");
        Point product8 = new Point("Olive oil", 71.7, 56.4, " 5 ");
        Point product9 = new Point("Soybean oil", 75.7, 65.8, " 5 ");
        Point product10 = new Point("Sausage", 66.9, 67.1, " 6 ");
        Point product11 = new Point("Ham", 84.7, 67.1, " 6 ");
        Point product12 = new Point("Basturma", 75.4, 78, " 6 ");
        Point product13 = new Point("Pork", 31.7, 78, " 6 9 ");
        Point product14 = new Point("Sujuk", 18, 78, " 6 10 ");
        Point product15 = new Point("Cheese", 97.4, 40.8, " 1 3 ");
        Point product16 = new Point("Butter", 97.4, 51.8, " 1 4 ");
        Point product17 = new Point("Sour cream", 97.4, 51.8, " 1 5 ");
        Point product18 = new Point("Chips", 52.6, 40.9, " 3 7 ");
        Point product19 = new Point("Cracker", 52.6, 51.2, " 4 7 ");
        Point product20 = new Point("Peanut", 52.6, 62.8, " 5 7 ");
        Point product21 = new Point("Rice", 50.5, 28.1, " 8 ");
        Point product22 = new Point("Buckwheat", 50.5, 52.2, " 8 ");
        Point product23 = new Point("Grain", 50.5, 63.3, " 8 ");
        Point product24 = new Point("Legumes", 39.6, 48, " 8 ");
        Point product25 = new Point("Dumplings", 26.4, 28.8, " 9 ");
        Point product26 = new Point("Cutlets", 36.3, 33.8, " 9 ");
        Point product27 = new Point("Puncakes", 26.4, 54, " 9 ");
        Point product28 = new Point("Dough", 36.3, 56.8, " 9 ");
        Point product29 = new Point("Mayonnaise", 12.9, 57.2, " 10 ");
        Point product30 = new Point("Ketchup", 23, 48.1, " 10 ");
        Point product31 = new Point("Jam", 12.9, 57.2, " 10 ");
        Point product32 = new Point("Coffee", 17.8, 16.3, " 10 12 ");
        Point product33 = new Point("Tea", 6.3, 16.3, " 11 12 ");
        Point product34 = new Point("Water", 9.8, 28.5, " 11 ");
        Point product35 = new Point("Juice", 9.8, 48.4, " 11 ");
        Point product36 = new Point("Compote", 9.8, 62.3, " 11 ");
        Point product37 = new Point("Beer", 1.4, 37.7, " 11 ");
        Point product38 = new Point("Wine", 1.4, 55.4, " 11 ");
        Point product39 = new Point("Cigarettes", 12, 13.2, " 0 ");
        products.add(entrance);
        products.add(cashbox1);
        products.add(cashbox2);
        products.add(cashbox3);
        products.add(cashbox4);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);
        products.add(product11);
        products.add(product12);
        products.add(product13);
        products.add(product14);
        products.add(product15);
        products.add(product16);
        products.add(product17);
        products.add(product18);
        products.add(product19);
        products.add(product20);
        products.add(product21);
        products.add(product22);
        products.add(product23);
        products.add(product24);
        products.add(product25);
        products.add(product26);
        products.add(product27);
        products.add(product28);
        products.add(product29);
        products.add(product30);
        products.add(product31);
        products.add(product32);
        products.add(product33);
        products.add(product34);
        products.add(product35);
        products.add(product36);
        products.add(product37);
        products.add(product38);
        products.add(product39);

        return new Shop(products, obstacles);
    }
}
