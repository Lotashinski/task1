package training.ammunition.ammunition;

import training.ammunition.Clothing;
import training.ammunition.WrongRangeException;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;

public class Pants extends Clothing {
    public Pants(double cost, double weight, String tradeName, Gender gender, Size size) throws WrongRangeException {
        super(cost, weight, tradeName, gender, size);
    }
}
