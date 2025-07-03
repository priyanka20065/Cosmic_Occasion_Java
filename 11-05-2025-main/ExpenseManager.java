import java.util.*;

public class ExpenseManager {
    private double budget;
    private List<Expense> expenses = new ArrayList<>();

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public void addExpense(Category category, double amount) {
        expenses.add(new Expense(category, amount));
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        expenses.sort(Comparator.comparingDouble(Expense::getAmount).reversed());
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void removeExpense(Category category) {
        expenses.removeIf(expense -> expense.getCategory() == category);
    }

    public double getTotalSpent() {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public double getBalance() {
        return budget - getTotalSpent();
    }
}
