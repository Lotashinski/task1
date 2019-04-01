package training.ammunition.ammunition;

import training.ammunition.Ammunition;
import training.ammunition.WrongRangeException;

public class Accessory extends Ammunition {
    private AccessoryType type;

    public AccessoryType getType() {
        return type;
    }

    public void setType(AccessoryType type) {
        this.type = type;
    }

    public Accessory(double cost, double weight, String tradeName) throws WrongRangeException {
        super(cost, weight, tradeName);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;

        Accessory accessory = (Accessory) obj;

        return this.getType().equals(accessory.getType());
    }
}
