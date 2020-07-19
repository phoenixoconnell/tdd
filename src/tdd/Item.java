package tdd;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
    private String name;
    private BigDecimal price;
    private int quantity;
    private boolean exempt;
    private boolean imported;

    public Item(String name, BigDecimal price, int quantity, boolean exempt, boolean imported) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.exempt = exempt;
        this.imported = imported;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isExempt() {
        return exempt;
    }

    public boolean isImported() {
        return imported;
    }

    public BigDecimal calculateTax(BigDecimal taxRate) {
        //Added test for new method during refactor phase
        BigDecimal tax = price.multiply(taxRate)
                .multiply(BigDecimal.valueOf(quantity));

        tax = roundToNearestFiveCents(tax);

        return tax;
    }

    protected BigDecimal roundToNearestFiveCents(BigDecimal tax) {
        //Added test for new method during refactor phase
        //Rounds to the nearest $0.05 using multiplication by 20 ($1.00/$0.05 = 20)
        BigDecimal newTax = tax.multiply(BigDecimal.valueOf(20))
                .setScale(0, RoundingMode.CEILING)
                .divide(BigDecimal.valueOf(20));

        return newTax;
    }
}
