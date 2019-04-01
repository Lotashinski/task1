package training.ammunition.ammunition;

import training.ammunition.Clothing;
import training.ammunition.WrongRangeException;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;
import training.ammunition.protection.DegreeOfProtection;
import training.ammunition.protection.Protection;
import training.ammunition.protection.ProtectionType;
import training.collector.protection.DegreOfProtectionSelector;

public class Hamlet extends Clothing implements Protection {
    private DegreeOfProtection degreeOfProtection;

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
    public ProtectionType getProtectionType() {
        return ProtectionType.HEAD;
    }

    public void setDegreeOfProtection(DegreeOfProtection degreeOfProtection) {
        this.degreeOfProtection = degreeOfProtection;
    }

    public Hamlet(double cost, double weight, String tradeName, Gender gender, Size size,
                  DegreeOfProtection degreeOfProtection, HamletType type) throws WrongRangeException {
        super(cost, weight, tradeName, gender, size);
        this.degreeOfProtection = degreeOfProtection;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;

        Hamlet hamlet = (Hamlet) obj;

        return this.getDegreeOfProtection().equals(hamlet.getDegreeOfProtection())
                && this.getType().equals(hamlet.getType());
    }
}
