package tdd;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    @Test
    public void itemInformationGettersAllWork() {
        Item item = new Item("test item", BigDecimal.valueOf(10.00), 2, true, false);

        String itemName = item.getName();
        BigDecimal price = item.getPrice();
        int quantity = item.getQuantity();
        boolean exemptStatus = item.isExempt();
        boolean importStatus = item.isImported();

        assertEquals("test item", itemName);
        assertEquals(BigDecimal.valueOf(10.00), price);
        assertEquals(2, quantity);
        assertEquals(true, exemptStatus);
        assertEquals(false, importStatus);
    }

    @Test
    public void roundingToNearestFiveCentsWorks() {
        //Added test for new method during refactor phase
        Item item = new Item("test item", BigDecimal.valueOf(10.00), 2, true, true);

        BigDecimal rounded = item.roundToNearestFiveCents(BigDecimal.valueOf(2.63));

        assertEquals(BigDecimal.valueOf(2.65), rounded);
    }

    @Test
    public void calculateTaxReturnsCorrectValue() {
        //Added test for new method during refactor phase
        Item item = new Item("test item", BigDecimal.valueOf(10.00), 2, true, true);

        BigDecimal tax = item.calculateTax(BigDecimal.valueOf(0.10));

        assertEquals(BigDecimal.valueOf(2), tax);
    }
}
