package training.motorcyclist;

import org.junit.Test;
import training.ammunition.WrongRangeException;
import training.ammunition.ammunition.*;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;
import training.ammunition.protection.DegreeOfProtection;

import static org.junit.Assert.*;

public class MotorcyclistTest {
    @Test
    public void createSetAntsBody() {
        try {
            Motorcyclist motocyclistSet = new Motorcyclist();
            motocyclistSet.setPants(new Pants(100, 100, "", Gender.MALE, Size.MEDIUM));
            motocyclistSet.setBody(new Jacket(1000, 100, "JACKET", Gender.UNISEXS, Size.MEDIUM,
                    new DegreeOfProtection(3), true));
            fail("conflict implemented");
        } catch (WrongRangeException e) {
            fail(String.format("initialization error %s", e.getMessage()));
        } catch (AmmunitionConflictException ammunitionConflictEcption) {
            assertTrue(true);
        }
    }

}