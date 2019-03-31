package training.motorcyclist;

public interface AmmunutionList {
    PriceList sortByName();

    PriceList sortByPrice();

    double totalPrice();

    PriceList getAmmunitionInRangeOfPrice(double minCost, double maxCost);
}
