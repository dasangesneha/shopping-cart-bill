import java.util.LinkedHashMap;
import java.util.Map;

public class Bill {
    private Inventory inventory;
    private Map<String, Integer> bill = new LinkedHashMap<>();

    public Bill(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addItemToBillByBarcode(String barcode) {
        int itemCount = bill.getOrDefault(barcode, 0);
        bill.put(barcode, ++itemCount);
    }

    public int getTotalItemCountThatAddedToBill(String barcode) {
        return bill.getOrDefault(barcode, 0);
    }

    public void deleteItemByBarcode(String barcode) {
        int itemCount = bill.get(barcode);

        if (itemCount == 1) {
            bill.remove(barcode);
        } else {
            bill.put(barcode, --itemCount);
        }
    }

    public String getLineItemBillDetails(String barcode) {
        Item item = inventory.getItemByBarcode(barcode);
        if (item != null) {
            int quantity = bill.get(barcode);
            return quantity + " x " + item.getName() + " @" + item.getPrice() + " = " + (item.getPrice() * quantity) + "\n";
        }
        return "";
    }

    public String getTotalBillAmount() {
        StringBuilder result = new StringBuilder();
        double totalBillAmount = 0;
        for (Map.Entry<String, Integer> lineItem : bill.entrySet()) {
            String barcode = lineItem.getKey();
            totalBillAmount += inventory.getItemByBarcode(barcode).getPrice() * lineItem.getValue();
            result.append(getLineItemBillDetails(barcode));
        }
        return result.append("Total = ").append(totalBillAmount).toString();
    }

}
