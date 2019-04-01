package training.collector.motorcyclist;

import training.ammunition.Ammunition;
import training.collector.AbstractCollector;
import training.collector.EnumSelector;
import training.collector.Message;
import training.collector.ValueException;
import training.collector.ammunition.AccessoryCreate;
import training.collector.ammunition.HamletCreate;
import training.collector.ammunition.JacketCreate;
import training.collector.ammunition.PantsCreate;
import training.motorcyclist.PriceList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PriceListCollector extends AbstractCollector {

    private PriceList priceList;

    private String[] meny = {
            "add accessory", // 0
            "add hamlet", // 1
            "add jacket", // 2
            "add pants", // 3
            "show", //4
            "back" // 5
    };

    public PriceList getPriceList() {
        return priceList;
    }

    private static AccessoryCreate accessoryCreate;
    private static HamletCreate hamletCreate;
    private static JacketCreate jacketCreate;
    private static PantsCreate pantsCreate;
    private static PriceSelector priceSelector;

    @Override
    public void conversationStart() throws ValueException {
        int answer;
        Ammunition ammunition;
        try {
            do {
                EnumSelector enumSelector = new EnumSelector(getSpeaker(),
                        Arrays.stream(meny).collect(Collectors.toList()),
                        "Select");
                answer = enumSelector.getValue();
                switch (answer) {
                    case 0:
                        accessoryCreate.conversationStart();
                        ammunition = accessoryCreate.getAccessory();
                        priceList.add(ammunition);
                        break;
                    case 1:
                        hamletCreate.conversationStart();
                        ammunition = hamletCreate.getHamlet();
                        priceList.add(ammunition);
                        break;
                    case 2:
                        jacketCreate.conversationStart();
                        ammunition = jacketCreate.getJacket();
                        priceList.add(ammunition);
                        break;
                    case 3:
                        pantsCreate.conversationStart();
                        ammunition = pantsCreate.getPants();
                        priceList.add(ammunition);
                        break;
                    case 4:
                        priceSelector.conversationStart();
                        priceSelector.conversationStart();
                        break;
                }
            } while (answer >= 0 && answer < meny.length - 1);
        } catch (ValueException e) {

            logger.catching(e);
            say(new Message("input error. Returned"));
        }
    }

    public PriceListCollector(Function<Message, Message> speaker) {
        this(speaker, new PriceList());
    }

    public PriceListCollector(Function<Message, Message> speaker, PriceList priceList) {
        super(speaker);
        if (accessoryCreate == null) {
            accessoryCreate = new AccessoryCreate(speaker);
            hamletCreate = new HamletCreate(speaker);
            jacketCreate = new JacketCreate(speaker);
            pantsCreate = new PantsCreate(speaker);
            priceSelector = new PriceSelector(speaker, priceList);
        }
        this.priceList = priceList;
    }

}
