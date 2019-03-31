package training.ammunition.ammunition;

import training.ammunition.Clothing;
import training.ammunition.WrongRangeException;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;
import training.ammunition.protection.DegreeOfProtection;
import training.ammunition.protection.Protection;

public class Body extends Clothing implements Protection {

    private DegreeOfProtection degreeOfProtection;

    private Size size;



    public Body(double cost, double weight, String tradeName, Gender gender, Size size,
                DegreeOfProtection degreeOfProtection, Size size1, boolean isOveralls) throws WrongRangeException {
        super(cost, weight, tradeName, gender, size);
        this.degreeOfProtection = degreeOfProtection;
        this.size = size1;
        this.isOveralls = isOveralls;
    }



    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public DegreeOfProtection getDegreeOfProtection() {
        return degreeOfProtection;
    }

    public void setDegreeOfProtection(DegreeOfProtection degreeOfProtection) {
        this.degreeOfProtection = degreeOfProtection;
    }


}
