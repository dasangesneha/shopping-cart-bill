import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillTest {
    private Bill bill;

    @BeforeEach
    public void setUp() {
        bill = new Bill();
    }

    @Test
    public void whenAddItemToBillThenItemGetsAdded() {
        bill.addItemToBillByBarcode(110011);

        assertEquals(1, bill.getTotalItemsCountThatAddedToBill());
    }

    @Test
    public void whenAddingSameItemTwiceThenItIsGettingAddedToBill() {
        bill.addItemToBillByBarcode(110011);

        bill.addItemToBillByBarcode(110011);

        assertEquals(2, bill.getTotalItemsCountThatAddedToBill());
    }

    @Test
    public void whenAddingDifferentItemToBillThenItIsGettingAddedToBill() {
        bill.addItemToBillByBarcode(110011);

        bill.addItemToBillByBarcode(110012);

        assertEquals(2, bill.getTotalItemsCountThatAddedToBill());
    }

    @Test
    public void whenRemoveItemFromBillThenItemGetsRemoved() {
        bill.addItemToBillByBarcode(110011);
        bill.addItemToBillByBarcode(110012);

        bill.deleteItemByBarcode(110011);

        assertEquals(1, bill.getTotalItemsCountThatAddedToBill());
    }

    @Test
    public void givenTwoItemsOfSameTypeAvailableWhenRemoveOneItemFromBillThenItemGetsRemoved() {
        bill.addItemToBillByBarcode(110011);
        bill.addItemToBillByBarcode(110011);
        bill.addItemToBillByBarcode(110012);

        bill.deleteItemByBarcode(110011);

        assertEquals(2, bill.getTotalItemsCountThatAddedToBill());
    }

}
