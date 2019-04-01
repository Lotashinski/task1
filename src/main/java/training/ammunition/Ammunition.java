package training.ammunition;

public abstract class Ammunition {

    private double cost;

    private double weight;

    private String tradeName;

    protected Ammunition(double cost, double weight, String tradeName) throws WrongRangeException {
        setCost(cost);
        setWeight(weight);
        setTradeName(tradeName);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) throws WrongRangeException {
        if (cost < 0) throw new WrongRangeException("cost can not be negative", cost);
        this.cost = cost;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws WrongRangeException {
        if (weight < 0) throw new WrongRangeException("weight can not be negative", weight);
        this.weight = weight;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj))
            return true;

        if (obj == null || !(obj.getClass().equals(this.getClass())))
            return false;

        Ammunition ammunition = (Ammunition) obj;

        return this.getTradeName().equals(ammunition.getTradeName())
                && this.getCost() == ammunition.getCost()
                && this.getWeight() == this.getWeight();
    }
}
