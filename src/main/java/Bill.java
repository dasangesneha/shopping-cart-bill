import java.util.HashMap;
import java.util.Map;

public class Bill {
    private Map<Integer, Integer> bill = new HashMap<>();

    public void addItemToBillByBarcode(int barcode) {
        int itemCount = bill.getOrDefault(barcode, 0);
        bill.put(barcode, ++itemCount);
    }

    public int getTotalItemsCountThatAddedToBill() {
        return bill.values().stream().reduce(Integer::sum).get();
    }

    public void deleteItemByBarcode(int barcode) {
        int itemCount = bill.get(barcode);

        if (itemCount == 1) {
            bill.remove(barcode);
        } else {
            bill.put(barcode, --itemCount);
        }
    }

}
