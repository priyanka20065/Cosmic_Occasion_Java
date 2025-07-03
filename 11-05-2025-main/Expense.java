public class Expense {
    private Category category;
    private double amount;

    public Expense(Category category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Category: " + category + ", Amount: Rs" + amount;
    }
}
