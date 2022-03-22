import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> itemList = new ArrayList<>();

    public void addItemToInventory(Item item) {
        itemList.add(item);
    }

    public Item getItemByBarcode(int barcode) {
        return itemList.stream().filter(item -> item.getBarcode() == barcode).findFirst().orElse(null);
    }

    public String getBillDetailsForLineItem(int barcode, int quantity) {
        Item item = getItemByBarcode(barcode);
        return quantity + " x " + item.getName() + " @" + item.getPrice() + " = " + (item.getPrice() * quantity) + "\n";
    }

}
