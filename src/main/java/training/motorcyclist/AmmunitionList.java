package training.motorcyclist;

import training.ammunition.Ammunition;
import training.collector.motorcyclist.PriceSelector;

public interface AmmunitionList {
    PriceList sortByName();

    PriceList sortByPrice();

    double totalPrice();

    PriceList getAmmunitionInRangeOfPrice(double minCost, double maxCost);
}
