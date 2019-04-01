package training.motorcyclist;

import training.ammunition.ammunition.Hamlet;
import training.ammunition.ammunition.Jacket;
import training.ammunition.ammunition.Pants;

public class Motorcyclist implements AmmunitionList {

    private PriceList accessory;

    private Hamlet hamlet;

    private Jacket body;

    private Pants pants;

    public Hamlet getHamlet() {
        return hamlet;
    }

    public void setHamlet(Hamlet hamlet) {
        this.hamlet = hamlet;
    }

    public Jacket getBody() {
        return body;
    }

    public PriceList getAccessory() {
        return accessory;
    }

    public void setAccessory(PriceList accessory) {
        if (accessory == null)
            throw new NullPointerException();
        this.accessory = accessory;
    }

    public void setBody(Jacket body) throws AmmunitionConflictException {
        if (body.isOveralls() && pants!= null)
            throw new AmmunitionConflictException("included overalls", body, getPants());
        this.body = body;
    }

    public Pants getPants() {
        return pants;
    }

    public void setPants(Pants pants) throws AmmunitionConflictException {
        if (getBody() != null && getBody().isOveralls())
            throw new AmmunitionConflictException("included overalls", getBody(), pants);
        this.pants = pants;
    }

    public Motorcyclist() {
        accessory = new PriceList();
    }

    public PriceList toPriceList() {
        PriceList priceList = new PriceList();
        if (hamlet != null)
            priceList.add(hamlet);
        priceList.addAll(accessory);
        if (body != null)
            priceList.add(body);
        if (pants != null)
            priceList.add(pants);
        return priceList;
    }

    @Override
    public PriceList sortByName() {
        return toPriceList().sortByName();
    }

    @Override
    public PriceList sortByPrice() {
        return toPriceList().sortByPrice();
    }

    @Override
    public double totalPrice() {
        return toPriceList().totalPrice();
    }

    @Override
    public PriceList getAmmunitionInRangeOfPrice(double minCost, double maxCost) {
        return toPriceList().getAmmunitionInRangeOfPrice(minCost, maxCost);
    }
}
