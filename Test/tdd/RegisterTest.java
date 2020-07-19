package tdd;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest {
    public Item nonExemptNonImported;
    public Item exemptNonImported;
    public Item nonExemptImported;
    public Item exemptImported;
    public Register register;

    @Before
    public void setUp() {
        //This will run before every test
        nonExemptNonImported = new Item("test item", BigDecimal.valueOf(10.00), 2, false, false);
        exemptNonImported = new Item("test item 2", BigDecimal.valueOf(5.00), 1, true, false);
        nonExemptImported = new Item("test item 3", BigDecimal.valueOf(22.50), 1, false, true);
        exemptImported = new Item("test item 4", BigDecimal.valueOf(4.75), 1, true, true);
        ArrayList<Item> cart = new ArrayList<>();
        cart.add(nonExemptNonImported);
        cart.add(exemptNonImported);
        cart.add(nonExemptImported);
        cart.add(exemptImported);
        register = new Register(cart);
    }

    @Test
    public void calculateCorrectPreTaxSubTotal() {
        //Added test for new method during refactor phase
        BigDecimal subTotal = register.getPreTaxSubTotal(nonExemptNonImported);

        assertEquals(BigDecimal.valueOf(20.00), subTotal);
    }

    @Test
    public void formattingCurrencyWorks() {
        //Added test for new method during refactor phase
        String formatted = register.formatCurrency(BigDecimal.valueOf(20.00));

        assertEquals("$20.00", formatted);
    }

    @Test
    public void checkingOutGeneratesCorrectValues() {
        register.checkOut();

        BigDecimal tax = register.getTax();
        BigDecimal total = register.getTotal();
        String receipt = register.getReceipt();

        assertEquals(BigDecimal.valueOf(5.65), tax);
        assertEquals(BigDecimal.valueOf(57.90).setScale(2, RoundingMode.UNNECESSARY), total);
        assertEquals("2 test item: $22.00\n1 test item 2: $5.00\n1 test item 3: $25.90\n1 test item 4: $5.00\nSales " +
                        "tax: $5.65 Total: $57.90",
                receipt);
    }
}
