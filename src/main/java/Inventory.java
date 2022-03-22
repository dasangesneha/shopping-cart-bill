import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Item> itemMap = new HashMap<>();

    public void addItemToInventory(Item item) {
        itemMap.put(item.getBarcode(), item);
    }

    public Item getItemByBarcode(String barcode) {
        return itemMap.getOrDefault(barcode, null);
    }

}
