package training.collector.motorcyclist;

import training.ammunition.Ammunition;
import training.ammunition.ammunition.Accessory;
import training.ammunition.ammunition.Hamlet;
import training.ammunition.ammunition.Jacket;
import training.ammunition.ammunition.Pants;
import training.collector.AbstractCollector;
import training.collector.EnumSelector;
import training.collector.Message;
import training.collector.ValueException;
import training.motorcyclist.AmmunitionConflictException;
import training.motorcyclist.Motorcyclist;
import training.motorcyclist.PriceList;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MotorcuclistCollector extends AbstractCollector {

    private static Motorcyclist motorcyclist;

    private static PriceSelector priceSelector;

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    private PriceList priceList;

    private String[] meny = {
            "set hamlet", // 0
            "set jacket", // 1
            "set pants", // 2
            "set accessory", // 3
            "break" // 4
    };

    @Override
    public void conversationStart() throws ValueException {
        int answer;
        try {
            do {
                EnumSelector enumSelector = new EnumSelector(getSpeaker(),
                        Arrays.stream(meny).collect(Collectors.toList()),
                        "Select");
                answer = enumSelector.getValue();
                PriceList priceList = new PriceList();
                switch (answer) {
                    case 0:
                        priceList.addAll(this.priceList.stream()
                                .filter(amo -> amo instanceof Hamlet).collect(Collectors.toList()));
                        priceSelector.setPriceList(priceList);
                        priceSelector.conversationStart();
                        if (PriceSelector.getAmmo() != null && PriceSelector.getAmmo() instanceof Hamlet)
                            motorcyclist.setHamlet((Hamlet) PriceSelector.getAmmo());
                        break;
                    case 1:
                        priceList.addAll(this.priceList.stream()
                                .filter(amo -> amo instanceof Jacket).collect(Collectors.toList()));
                        priceSelector.setPriceList(priceList);
                        priceSelector.conversationStart();
                        if (PriceSelector.getAmmo() != null && PriceSelector.getAmmo() instanceof Jacket) {
                            try {
                                Jacket jacket = (Jacket) PriceSelector.getAmmo();
                                if (jacket.isOveralls() && motorcyclist.getPants() != null)
                                    motorcyclist.setPants(null);
                                motorcyclist.setBody(jacket);
                            } catch (AmmunitionConflictException ex) {
                                logger.info("magic");
                            }
                        }
                        break;
                    case 2:
                        priceList.addAll(this.priceList.stream()
                                .filter(amo -> amo instanceof Pants).collect(Collectors.toList()));
                        priceSelector.setPriceList(priceList);
                        priceSelector.conversationStart();
                        if (PriceSelector.getAmmo() != null && PriceSelector.getAmmo() instanceof Pants) {
                            try {
                                Pants pants = (Pants) PriceSelector.getAmmo();
                                if (motorcyclist.getBody() != null && motorcyclist.getBody().isOveralls())
                                    motorcyclist.setBody(null);
                                motorcyclist.setPants(pants);
                            } catch (AmmunitionConflictException ex) {
                                logger.info("magic");
                            }
                        }
                        break;
                    case 3:
                        priceList.addAll(this.priceList.stream()
                                .filter(amo -> amo instanceof Accessory).collect(Collectors.toList()));
                        priceSelector.setPriceList(priceList);
                        priceSelector.conversationStart();
                        if (PriceSelector.getAmmo() != null && PriceSelector.getAmmo() instanceof Accessory)
                            motorcyclist.getAccessory().add((Accessory) PriceSelector.getAmmo());
                        break;
                }
            } while (answer >= 0 && answer < meny.length - 1);
        } catch (ValueException e) {

            logger.catching(e);
            say(new Message("input error. Returned"));
        }
    }

    public MotorcuclistCollector(Function<Message, Message> speaker, PriceList priceList) {
        super(speaker);


        if (priceSelector == null) {
            motorcyclist = new Motorcyclist();
            priceSelector = new PriceSelector(speaker, motorcyclist.toPriceList());
        }
    }
}
