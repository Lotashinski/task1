package training.motorcyclist;

import training.ammunition.Ammunition;

public class AmmunitionConflictEcption extends Exception {

    private Ammunition included;

    private Ammunition added;

    public Ammunition getIncluded() {
        return included;
    }

    public Ammunition getAdded() {
        return added;
    }

//    public AmmunitionConflictEcption(Ammunition included, Ammunition added) {
//        this.included = included;
//        this.added = added;
//    }

    public AmmunitionConflictEcption(String message, Ammunition included, Ammunition added) {
        super(message);
        this.included = included;
        this.added = added;
    }
}
