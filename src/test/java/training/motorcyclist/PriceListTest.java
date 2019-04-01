package training.motorcyclist;

import org.junit.Test;
import training.ammunition.Ammunition;
import training.ammunition.WrongRangeException;
import training.ammunition.ammunition.Hamlet;
import training.ammunition.ammunition.HamletType;
import training.ammunition.ammunition.Jacket;
import training.ammunition.ammunition.Pants;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;
import training.ammunition.protection.DegreeOfProtection;

import java.util.Arrays;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class PriceListTest {

    private PriceList priceList;

    public PriceListTest() {
        priceList = new PriceList();
        try {
            for (int i = 0; i < 6; ++i) {
                priceList.add(new Pants(2000, 0.5 * i, String.format("Z_PANTS %d", i * 2),
                        Gender.UNISEXS, Size.MEDIUM));
                priceList.add(new Hamlet(1000, i * 2, String.format("A_BUSTER %d", i),
                        Gender.UNISEXS, Size.MEDIUM, new DegreeOfProtection(i), HamletType.FULL_FACE));
                priceList.add(new Jacket(1000, i * 2, String.format("JACKET %d", i),
                        Gender.UNISEXS, Size.MEDIUM, new DegreeOfProtection(i), false));
            }
        } catch (WrongRangeException e) {
//            StringBuffer buffer = new StringBuffer();
//            Arrays.stream(e.getStackTrace())
//                    .forEach(stackTraceElement -> {
//                        buffer.append("\n");
//                        buffer.append(stackTraceElement.toString());
//                    });
            fail(String.format("created error : %s ", e.getMessage()/*, buffer.toString()*/));
        }
    }

    @Test
    public void sortByName() {
        this.priceList.sortByName();
        for (int i = 0; i < priceList.size() - 1; ++i) {
            String first = priceList.get(i).getTradeName();
            String second = priceList.get(i + 1).getTradeName();
            if (String.CASE_INSENSITIVE_ORDER.compare(first, second) > 0) {
                fail(String.format("first : %s\nsecond : %s", first, second));
                return;
            }
        }
    }

    @Test
    public void totalPrice() {
        double total = priceList.totalPrice();
        if (total != 24000.0)
            fail(String.format("Result price %f, (!= 24 000)", total));
    }

    @Test
    public void getAmmunitionInRangeOfPrice() {
        priceList
                .getAmmunitionInRangeOfPrice(1000, 1000)
                .forEach(ammunition -> {
                    if (ammunition instanceof Pants) {
                        fail("Ammunition price out of rage");
                        return;
                    }
                });
    }
}