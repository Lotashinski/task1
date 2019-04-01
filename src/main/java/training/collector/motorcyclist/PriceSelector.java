package training.collector.motorcyclist;

import training.ammunition.Ammunition;
import training.ammunition.protection.Protection;
import training.collector.AbstractCollector;
import training.collector.EnumSelector;
import training.collector.Message;
import training.collector.ValueException;
import training.motorcyclist.PriceList;

import java.nio.channels.Selector;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PriceSelector extends AbstractCollector {

    private PriceList priceList;

    private static Ammunition ammo;

    public static Ammunition getAmmo() {
        return ammo;
    }

    private String[] meny = {
            "sort by name", // 0
            "filter by price", // 1
            "sort by weight", //2
            "back" // 3
    };

    private static String getString(Ammunition ammunition) {
        return String.format("%10s | %10s | %8.2f $ | %8.3f kg | ",
                ammunition.getTradeName(),
                ammunition.getClass(),
                ammunition.getCost(),
                ammunition.getWeight()) + ((ammunition instanceof Protection)
                ? ((Protection) ammunition).getDegreeOfProtection()
                .getValue() : "-");
    }

    @Override
    public void conversationStart() throws ValueException {
        int answer;
        try {
            do {
                List<String> vars = new LinkedList<String>();
                if (!vars.addAll(Arrays.stream(meny).collect(Collectors.toList())) &&
                        !vars.addAll(priceList.stream().map(PriceSelector::getString).collect(Collectors.toList()))) {
                    logger.error("menu create error");
                }

                EnumSelector enumSelector = new EnumSelector(getSpeaker(), vars, "Select");
                answer = enumSelector.getValue();
                switch (answer) {
                    case 0:
                        (new PriceSelector(getSpeaker(), priceList.sortByName()))
                                .conversationStart();
                        break;
                    case 1:
                        double min = questionDouble(new Message("set min price"));
                        if (min > 0) {
                            say(new Message("input error"));
                            continue;
                        }
                        double max = questionDouble(new Message("set max price"));
                        if (min > max) {
                            say(new Message("input error"));
                            continue;
                        }
                        (new PriceSelector(getSpeaker(), priceList.getAmmunitionInRangeOfPrice(min, max)))
                                .conversationStart();
                        break;
                    case 2:
                        (new PriceSelector(getSpeaker(), priceList.sortByPrice())).conversationStart();
                        break;
                    case 3:
                        return;
                }
                if (answer > 3)
                    ammo = priceList.get(answer - 4);
            } while (answer >= 0 && answer < meny.length - 1);
        } catch (ValueException e) {
            say(new Message("input error. Returned"));
        }
    }

    public PriceSelector(Function<Message, Message> speaker, PriceList priceList) {
        super(speaker);
        this.priceList = priceList;
    }
}
