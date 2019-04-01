package training.collector.ammunition;

import training.ammunition.WrongRangeException;
import training.ammunition.ammunition.Hamlet;
import training.ammunition.ammunition.HamletType;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;
import training.collector.AbstractCollector;
import training.collector.EnumSelector;
import training.collector.Message;
import training.collector.ValueException;
import training.collector.protection.DegreOfProtectionSelector;

import java.util.function.Function;

public class HamletCreate extends AbstractCollector {
    private Hamlet hamlet;

    public Hamlet getHamlet() {
        return hamlet;
    }

    @Override
    public void conversationStart() throws ValueException {
        try {
            hamlet = new Hamlet(
                    questionDouble(new Message("Cost : ")),
                    questionDouble(new Message("Weight : ")),
                    question(new Message("Trade name:")).getMain(),
                    Gender.values()[(new EnumSelector(getSpeaker(), genders, "Gender select:")).getValue()],
                    Size.values()[(new EnumSelector(getSpeaker(), sizes, "Size select:")).getValue()],
                    (new DegreOfProtectionSelector(getSpeaker())).getDegree(),
                    HamletType.values()[(new EnumSelector(getSpeaker(), hamletTypes, "Hamlet Type Select select:"))
                            .getValue()]
                    );
        } catch (WrongRangeException e) {
            logger.info(e);
        }catch (ValueException e)
        {
            logger.info(e);
        }
    }

    public HamletCreate(Function<Message, Message> speaker) {
        super(speaker);
    }
}
