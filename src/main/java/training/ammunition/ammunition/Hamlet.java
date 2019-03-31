package training.ammunition.jacket;

import training.ammunition.Ammunition;
import training.ammunition.WrongRangeException;
import training.ammunition.clothing.Clothing;
import training.ammunition.clothing.Size;
import training.ammunition.protection.DegreeOfProtection;
import training.ammunition.protection.Protection;

public class Hamlet extends Ammunition implements Protection, Clothing {
    private DegreeOfProtection degreeOfProtection;

    private Size size;

    private HamletType type;

    public HamletType getType() {
        return type;
    }

    public void setType(HamletType type) {
        this.type = type;
    }

    @Override
    public DegreeOfProtection getDegreeOfProtection() {
        return degreeOfProtection;
    }

    @Override
    public void setDegreeOfProtection(DegreeOfProtection degreeOfProtection) {
        this.degreeOfProtection = degreeOfProtection;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    public Hamlet(double cost, double weight, String tradeName, DegreeOfProtection degreeOfProtection, Size size,
                  HamletType type) throws WrongRangeException {
        super(cost, weight, tradeName);
        this.degreeOfProtection = degreeOfProtection;
        this.size = size;
        this.type = type;
    }
}
