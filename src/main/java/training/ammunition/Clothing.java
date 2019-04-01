
package training.ammunition;

import training.ammunition.Ammunition;
import training.ammunition.WrongRangeException;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;

abstract public class Clothing extends Ammunition implements training.ammunition.clothing.Clothing {

    private Gender gender;

    private Size size;

    public Clothing(double cost, double weight, String tradeName, Gender gender, Size size) throws WrongRangeException {
        super(cost, weight, tradeName);
        this.gender = gender;
        this.size = size;
    }

    @Override
    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;

        Clothing clothing = (Clothing) obj;

        return this.getGender().equals(clothing.getGender())
                && this.getSize().equals(clothing.getSize());
    }
}
