package training.collector.ammunition;

import training.ammunition.WrongRangeException;
import training.ammunition.ammunition.Jacket;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;
import training.collector.AbstractCollector;
import training.collector.EnumSelector;
import training.collector.Message;
import training.collector.ValueException;

import java.util.function.Function;

public class JacketCreate extends AbstractCollector {
    private Jacket jacket;

    public JacketCreate(Function<Message, Message> speaker) {
        super(speaker);
    }

    public Jacket getJacket() {
        return jacket;
    }

    @Override
    public void conversationStart() throws ValueException {
        try {
            jacket = new Jacket(
                    questionDouble(new Message("Cost : ")),
                    questionDouble(new Message("Weight : ")),
                    question(new Message("Trade name:")).getMain(),
                    Gender.values()[(new EnumSelector(getSpeaker(), genders, "Gender select:")).getValue()],
                    Size.values()[(new EnumSelector(getSpeaker(), sizes, "Size select:")).getValue()],
                    questionBool(new Message("is overalls ? (y / n)"))
            );
        } catch (WrongRangeException e) {
            logger.info(e);
        }catch (ValueException e)
        {
            logger.info(e);
        }
    }
}
