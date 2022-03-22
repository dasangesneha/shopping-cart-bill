import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BillTest {
    private Bill bill;
    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = new Inventory();

        Item apple = new Item("110011", "Apple", 1.1);
        Item banana = new Item("110022", "Banana", 1.3);
        Item carrot = new Item("110033", "Carrot", 0.9);

        inventory.addItemToInventory(apple);
        inventory.addItemToInventory(banana);
        inventory.addItemToInventory(carrot);

        bill = new Bill(inventory);
    }

    @Test
    public void whenAddItemToBillThenItemGetsAdded() {
        String expectedBarcode = "110011";

        bill.addItemToBillByBarcode(expectedBarcode);

        assertEquals(1, bill.getTotalItemCountThatAddedToBill(expectedBarcode));
    }

    @Test
    public void whenAddingAppleTwiceThenItIsGettingAddedToBill() {
        String barcodeForApple = "110011";
        bill.addItemToBillByBarcode(barcodeForApple);

        bill.addItemToBillByBarcode(barcodeForApple);

        assertEquals(2, bill.getTotalItemCountThatAddedToBill(barcodeForApple));
    }

    @Test
    public void givenBillContainsAppleWhenAddingBananaThenItIsGettingAddedToBill() {
        String barcodeForApple = "110011";
        String barcodeForBanana = "110012";

        bill.addItemToBillByBarcode(barcodeForApple);
        bill.addItemToBillByBarcode(barcodeForBanana);

        assertEquals(1, bill.getTotalItemCountThatAddedToBill(barcodeForApple));
        assertEquals(1, bill.getTotalItemCountThatAddedToBill(barcodeForBanana));
    }

    @Test
    public void whenRemoveItemFromBillThenItemGetsRemoved() {
        String barcodeForApple = "110011";
        bill.addItemToBillByBarcode(barcodeForApple);

        bill.deleteItemByBarcode(barcodeForApple);

        assertEquals(0, bill.getTotalItemCountThatAddedToBill(barcodeForApple));
    }

    @Test
    public void whenRemovedOneAppleThenItGetsRemovedAndOthersAreNotRemoved() {
        String barcodeForApple = "110011";
        String barcodeForBanana = "110012";
        bill.addItemToBillByBarcode(barcodeForApple);
        bill.addItemToBillByBarcode(barcodeForApple);
        bill.addItemToBillByBarcode(barcodeForBanana);

        bill.deleteItemByBarcode(barcodeForApple);

        assertEquals(1, bill.getTotalItemCountThatAddedToBill(barcodeForApple));
        assertEquals(1, bill.getTotalItemCountThatAddedToBill(barcodeForBanana));
    }

    @Test
    public void whenGetLineItemDetailsThenPrintTheBillLine() {
        String barcodeForApple = "110011";
        String expectedResult = "1 x Apple @1.1 = 1.1\n";
        bill.addItemToBillByBarcode(barcodeForApple);

        String actualResult = bill.getLineItemBillDetails(barcodeForApple);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void givenBarcodeNotExistsWhenGetLineItemDetailsThenDoNOtReturnAnything() {
        bill.addItemToBillByBarcode("110011");

        String actualResult = bill.getLineItemBillDetails("110012");

        assertEquals("", actualResult);
    }

    @Test
    public void whenCalculateTotalBillThenPrintTheTotalBill() {
        String expectedResult = "2 x Apple @1.1 = 2.2\n1 x Banana @1.3 = 1.3\nTotal = 3.5";
        String barcodeForApple = "110011";
        String barcodeForBanana = "110022";
        bill.addItemToBillByBarcode(barcodeForApple);
        bill.addItemToBillByBarcode(barcodeForApple);
        bill.addItemToBillByBarcode(barcodeForBanana);

        String actualResult = bill.getTotalBillAmount();

        assertEquals(expectedResult, actualResult);
    }

}
