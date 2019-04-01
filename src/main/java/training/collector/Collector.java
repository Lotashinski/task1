package training.collector;

import sun.rmi.runtime.Log;
import training.ammunition.WrongRangeException;
import training.collector.motorcyclist.MotorcuclistCollector;
import training.collector.motorcyclist.PriceListCollector;
import training.motorcyclist.Motorcyclist;
import training.motorcyclist.PriceList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Collector extends AbstractCollector {

    private PriceList priceList;

    private MotorcuclistCollector motorcuclistCollector;

    private PriceListCollector priceListCollector;

    private String[] meny = {
            "motorcuclist",
            "pricelist",
            "exit"
    };

    @Override
    public void conversationStart() {
        int answer = 0;
        do {
            try {


                EnumSelector enumSelector = new EnumSelector(getSpeaker(),
                        Arrays.stream(meny).collect(Collectors.toList()),
                        "Select");
                answer = enumSelector.getValue();
                switch (answer) {
                    case 0:
                        motorcuclistCollector.conversationStart();
                        break;
                    case 1:
                        priceListCollector.conversationStart();
                    case 3:
                        return;
                }
            } catch (ValueException e) {
                logger.info("wrong user data " + e.getMessage());

//            } catch (WrongRangeException e) {
//                logger.info("wrong user data " + e.getMessage());
//
            } finally {
                if (answer != 3)
                    answer = 0;
            }
        } while (answer >= 0 && answer < meny.length - 1);
    }

    public Collector(Function<Message, Message> speaker) {
        super(speaker);
        priceList = new PriceList();
        motorcuclistCollector = new MotorcuclistCollector(speaker, priceList);
        priceListCollector = new PriceListCollector(speaker, priceList);
    }
}
