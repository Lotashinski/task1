package training.ammunition.ammunition;

import training.ammunition.Clothing;
import training.ammunition.WrongRangeException;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;
import training.ammunition.protection.DegreeOfProtection;
import training.ammunition.protection.Protection;
import training.ammunition.protection.ProtectionType;

public class Jacket extends Clothing implements Protection {

    private DegreeOfProtection degreeOfProtection;

    private boolean isOveralls;

    public Jacket(double cost, double weight, String tradeName, Gender gender, Size size,
                  DegreeOfProtection degreeOfProtection, boolean isOveralls) throws WrongRangeException {
        super(cost, weight, tradeName, gender, size);
        this.degreeOfProtection = degreeOfProtection;
        this.isOveralls = isOveralls;
    }

    public Jacket(double cost, double weight, String tradeName, Gender gender, Size size, boolean isOveralls) throws WrongRangeException {
        this(cost, weight, tradeName, gender, size, new DegreeOfProtection(), isOveralls);
    }

    public boolean isOveralls() {
        return isOveralls;
    }

    public void setOveralls(boolean overalls) {
        isOveralls = overalls;
    }

    @Override
    public ProtectionType getProtectionType() {
        return isOveralls() ? ProtectionType.COMBINIED : ProtectionType.BODY;
    }

    @Override
    public DegreeOfProtection getDegreeOfProtection() {
        return degreeOfProtection;
    }

    public void setDegreeOfProtection(DegreeOfProtection degreeOfProtection) {
        this.degreeOfProtection = degreeOfProtection;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;

        Jacket jacket = (Jacket)obj;

        return this.isOveralls() == jacket.isOveralls()
                && this.getDegreeOfProtection().equals(jacket.getDegreeOfProtection())
                && this.getSize().equals(jacket.getSize());
    }
}
