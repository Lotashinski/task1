package training.collector.ammunition;

import training.ammunition.WrongRangeException;
import training.ammunition.ammunition.Jacket;
import training.ammunition.ammunition.Pants;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;
import training.collector.AbstractCollector;
import training.collector.EnumSelector;
import training.collector.Message;
import training.collector.ValueException;

import java.util.function.Function;

public class PantsCreate extends AbstractCollector {

    private Pants pants;

    public Pants getPants() {
        return pants;
    }

    @Override
    public void conversationStart() throws ValueException {
        try {
            pants = new Pants(
                    questionDouble(new Message("Cost : ")),
                    questionDouble(new Message("Weight : ")),
                    question(new Message("Trade name:")).getMain(),
                    Gender.values()[(new EnumSelector(getSpeaker(), genders, "Gender select:")).getValue()],
                    Size.values()[(new EnumSelector(getSpeaker(), sizes, "Size select:")).getValue()]
            );
        } catch (WrongRangeException e) {
            logger.info(e);
        }catch (ValueException e)
        {
            logger.info(e);
        }
    }

    public PantsCreate(Function<Message, Message> speaker) {
        super(speaker);
    }
}
