package br.com.jamesson.coffee;

public class Cafe {

    private int beansInStock = 0;
    private int milkInStock = 0;

    public Coffee brew(CoffeeType coffeeType) {
        return brew(coffeeType, 1);
    }

    public Coffee brew(CoffeeType coffeeType, int strength) {
        requirePositive(strength);

        int requiredBeans = coffeeType.getRequiredBeans() * strength;
        int requiredMilk = coffeeType.getRequiredMilk() * strength;
        if (requiredBeans > beansInStock || requiredMilk > milkInStock) {
            throw new IllegalStateException("Insufficient beans or milk");
        }

        beansInStock -= requiredBeans;
        milkInStock -= requiredMilk;
        return new Coffee(coffeeType, requiredBeans, requiredMilk);
    }

    public void restockBeans(int weightInGrams) {
        requirePositive(weightInGrams);
        beansInStock += weightInGrams;
    }

    public void restockMilk(int weightInGrams) {
        requirePositive(weightInGrams);
        milkInStock += weightInGrams;
    }

    private void requirePositive(int value) {
        if (value < 1) {
            throw new IllegalArgumentException();
        }
    }

    public int getBeansInStock() {
        return beansInStock;
    }

    public int getMilkInStock() {
        return milkInStock;
    }

}
