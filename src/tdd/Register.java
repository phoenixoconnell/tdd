package tdd;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Register {
    private ArrayList<Item> cart;
    private String receipt;
    private BigDecimal taxRate;
    private BigDecimal importRate;
    private BigDecimal tax;
    private BigDecimal total;

    public Register(ArrayList<Item> cart) {
        this.cart = cart;
        this.receipt = "";
        this.taxRate = BigDecimal.valueOf(0.1);
        this.importRate = BigDecimal.valueOf(0.05);
        this.tax = BigDecimal.valueOf(0.0);
        this.total = BigDecimal.valueOf(0.0);
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getReceipt() {
        return receipt;
    }

    public void checkOut() {
        //Loops through the items in a cart
        //Both taxes are initialized to 0
        //Updates tax amounts based on import and exemption status
        //Updates totals and receipt with tax values
        for(Item item: cart) {
            BigDecimal subTotal = getPreTaxSubTotal(item);
            BigDecimal importTax = BigDecimal.valueOf(0.0);
            BigDecimal itemTax = BigDecimal.valueOf(0.0);

            if(item.isExempt()) {
                if(item.isImported()) {
                    importTax = item.calculateTax(importRate);
                }
            } else {
                if(item.isImported()) {
                    itemTax = item.calculateTax(taxRate);
                    importTax = item.calculateTax(importRate);
                } else {
                    itemTax = item.calculateTax(taxRate);
                }
            }
            subTotal = subTotal.add(itemTax).add(importTax);
            this.tax = this.tax.add(importTax).add(itemTax);


            this.total = this.total.add(subTotal);
            this.receipt += "" + item.getQuantity() + " " + item.getName() + ": " + formatCurrency(subTotal) + "\n";
        }

        this.receipt += "Sales tax: " + formatCurrency(tax) + " Total: " + formatCurrency(total);
    }

    protected BigDecimal getPreTaxSubTotal(Item item) {
        //Added test for new method during refactor phase
        return item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
    }

    protected String formatCurrency(BigDecimal amount) {
        //Added test for new method during refactor phase
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    public static void main(String[] args) {
        Item book = new Item("book", BigDecimal.valueOf(12.49), 1, true, false);
        Item musicCD = new Item("music CD", BigDecimal.valueOf(14.99), 1, false, false);
        Item chocolate = new Item("chocolate bar", BigDecimal.valueOf(0.85), 1, true, false);
        ArrayList<Item> cart1 = new ArrayList<>();

        cart1.add(book);
        cart1.add(musicCD);
        cart1.add(chocolate);

        Register register1 = new Register(cart1);
        register1.checkOut();

        String receipt1 = register1.getReceipt();
        System.out.println(receipt1);
        System.out.println();

        Item chocolates = new Item("imported box of chocolates", BigDecimal.valueOf(10.00), 1, true, true);
        Item perfume = new Item("imported bottle of perfume", BigDecimal.valueOf(47.50), 1, false, true);
        ArrayList<Item> cart2 = new ArrayList<>();

        cart2.add(chocolates);
        cart2.add(perfume);

        Register register2 = new Register(cart2);
        register2.checkOut();

        String receipt2 = register2.getReceipt();
        System.out.println(receipt2);
        System.out.println();

        Item perfume2 = new Item("imported bottle of perfume", BigDecimal.valueOf(27.99), 1, false, true);
        Item perfume3 = new Item("bottle of perfume", BigDecimal.valueOf(18.99), 1, false, false);
        Item pills = new Item("headache pills", BigDecimal.valueOf(9.75), 1, true, false);
        Item chocolates2 = new Item("imported box of chocolates", BigDecimal.valueOf(11.25), 1, true, true);
        ArrayList<Item> cart3 = new ArrayList<>();

        cart3.add(perfume2);
        cart3.add(perfume3);
        cart3.add(pills);
        cart3.add(chocolates2);

        Register register3 = new Register(cart3);
        register3.checkOut();

        String receipt3 = register3.getReceipt();
        System.out.println(receipt3);
        System.out.println();

    }
}

//.multiply(BigDecimal.valueOf(20)).round(new MathContext(0, RoundingMode.CEILING)).divide(BigDecimal.valueOf(20))