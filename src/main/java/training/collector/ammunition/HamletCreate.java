package training.collector.ammunition;

import training.ammunition.WrongRangeException;
import training.ammunition.ammunition.Accessory;
import training.collector.Message;
import training.collector.ValueException;

import java.util.function.Function;

public class HelmetCreate {
    private Accessory accessory;

    @Override
    public void conversationStart() throws ValueException {
        try {
            accessory = new Accessory(
                    questionDouble(new Message("Cost : ")),
                    questionDouble(new Message("Weight : ")),
                    question(new Message("Trade name:")).getMain()
            );
        } catch (WrongRangeException e) {
            logger.info(e);
        }
    }

    public AccessoryCreate(Function<Message, Message> speaker) {
        super(speaker);
    }
}
