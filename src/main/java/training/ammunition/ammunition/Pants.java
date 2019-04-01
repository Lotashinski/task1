package training.ammunition.ammunition;

import training.ammunition.Clothing;
import training.ammunition.WrongRangeException;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;
import training.ammunition.protection.DegreeOfProtection;
import training.ammunition.protection.Protection;
import training.ammunition.protection.ProtectionType;

public class Pants extends Clothing implements Protection {

    private DegreeOfProtection degreeOfProtection;

    @Override
    public DegreeOfProtection getDegreeOfProtection() {
        return degreeOfProtection;
    }

    @Override
    public ProtectionType getProtectionType() {
        return ProtectionType.ARMS;
    }

    public void setDegreeOfProtection(DegreeOfProtection degreeOfProtection) {
        this.degreeOfProtection = degreeOfProtection;
    }

    public Pants(double cost, double weight, String tradeName, Gender gender, Size size) throws WrongRangeException {
        this(cost, weight, tradeName, gender, size, new DegreeOfProtection());
    }

    public Pants(double cost, double weight, String tradeName, Gender gender, Size size, DegreeOfProtection degreeOfProtection) throws WrongRangeException {
        super(cost, weight, tradeName, gender, size);
        this.degreeOfProtection = degreeOfProtection;
    }
}
