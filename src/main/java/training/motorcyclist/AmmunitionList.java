package training.motorcyclist;

public interface AmmunitionList {
    PriceList sortByName();

    PriceList sortByPrice();

    double totalPrice();

    PriceList getAmmunitionInRangeOfPrice(double minCost, double maxCost);
}
