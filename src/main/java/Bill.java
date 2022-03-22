import java.util.LinkedHashMap;
import java.util.Map;

public class Bill {
    private Inventory inventory;
    private Map<Integer, Integer> bill = new LinkedHashMap<>();

    public Bill(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addItemToBillByBarcode(int barcode) {
        int itemCount = bill.getOrDefault(barcode, 0);
        bill.put(barcode, ++itemCount);
    }

    public int getTotalItemCountThatAddedToBill(int barcode) {
        return bill.getOrDefault(barcode, 0);
    }

    public void deleteItemByBarcode(int barcode) {
        int itemCount = bill.get(barcode);

        if (itemCount == 1) {
            bill.remove(barcode);
        } else {
            bill.put(barcode, --itemCount);
        }
    }

    public String getLineItemBillDetails(int barcode) {
        return inventory.getBillDetailsForLineItem(barcode, bill.get(barcode));
    }

    public String getTotalBillAmount() {
        StringBuilder result = new StringBuilder();
        double totalBillAmount = 0;
        for (Map.Entry<Integer, Integer> lineItem : bill.entrySet()) {
            int barcode = lineItem.getKey();
            totalBillAmount += inventory.getItemByBarcode(barcode).getPrice() * lineItem.getValue();
            result.append(getLineItemBillDetails(barcode));
        }
        return result.append("Total = " + totalBillAmount).toString();
    }

}
