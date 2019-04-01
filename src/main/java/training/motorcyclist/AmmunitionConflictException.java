package training.motorcyclist;

import training.ammunition.Ammunition;

public class AmmunitionConflictException extends Exception {

    private Ammunition included;

    private Ammunition added;

    public Ammunition getIncluded() {
        return included;
    }

    public Ammunition getAdded() {
        return added;
    }

//    public AmmunitionConflictException(Ammunition included, Ammunition added) {
//        this.included = included;
//        this.added = added;
//    }

    public AmmunitionConflictException(String message, Ammunition included, Ammunition added) {
        super(message);
        this.included = included;
        this.added = added;
    }
}
