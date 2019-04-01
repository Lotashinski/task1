package training.ammunition.protection;

import training.ammunition.WrongRangeException;

public final class DegreeOfProtection {

    static final int MIN_VALUE = 0;
    static final int MAX_VALUE = 5;

    public static int getMinValue() {
        return MIN_VALUE;
    }

    public static int getMaxValue() {
        return MAX_VALUE;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public DegreeOfProtection(){
        this.value = DegreeOfProtection.getMinValue();
    }

    public DegreeOfProtection(int value) throws WrongRangeException {
        if (value < MIN_VALUE || value > MAX_VALUE) throw new WrongRangeException("value is out of range", value);
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (obj instanceof DegreeOfProtection)
            return ((DegreeOfProtection)obj).getValue() == this.getValue();
        return false;
    }
}
