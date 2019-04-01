package training.motorcyclist;

import training.ammunition.Ammunition;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class PriceList extends LinkedList<Ammunition> implements AmmunitionList {

    @Override
    public PriceList sortByName() {
        this.sort((ammunition, t1) -> String.CASE_INSENSITIVE_ORDER.compare(ammunition.getTradeName(), t1.getTradeName()));
        return this;
    }

    @Override
    public PriceList sortByPrice() {
        this.sort((Comparator.comparingDouble(Ammunition::getCost)));
        return this;
    }

    @Override
    public double totalPrice() {
        return this.stream().mapToDouble(Ammunition::getCost).sum();
    }

    @Override
    public PriceList getAmmunitionInRangeOfPrice(double minCost, double maxCost) {
        return this.stream()
                .filter(ammunition -> ammunition.getCost() >= minCost && ammunition.getCost() <= maxCost)
                .collect(Collectors.toCollection(PriceList::new));
    }
}
