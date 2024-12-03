import java.util.ArrayList;
import java.util.List;

public class Client
{
    public String name;
    public List<String> shoppingList = new ArrayList<String>();
    public String shoppingItems;
    public Client(String name, List<String> shoppingList)
    {
        this.name = name;
        this.shoppingList = shoppingList;
        shoppingItems();
    }
    public void shoppingItems()
    {
        String items = "";
        for (String item : shoppingList)
            items = items.concat(item.toLowerCase() + " ");
        this.shoppingItems = items;
    }
}