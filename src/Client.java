import java.util.*;
import java.util.List;

public class Client
{
    public String name;
    public List<String> shoppingList = new ArrayList<String>();
    public String shoppingItems;

    public Client (String shoppingItems)
    {
        this.shoppingItems = shoppingItems;
    }
    public Client (String name, List<String> shoppingList)
    {
        this.name = name;
        this.shoppingList = shoppingList;
        shoppingItemsConverter();
    }
    public void shoppingItemsConverter()
    {
        String items = "";
        for (String item : shoppingList)
            items = items.concat(item.toLowerCase() + " ");
        this.shoppingItems = items;
    }
    public String getShoppingItems()
    {
        return shoppingItems;
    }
}
