package training.collector.protection;

import training.ammunition.WrongRangeException;
import training.ammunition.clothing.Size;
import training.ammunition.protection.DegreeOfProtection;
import training.collector.AbstractCollector;
import training.collector.Message;
import training.collector.ValueException;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DegreOfProtectionSelector extends AbstractCollector {

    private DegreeOfProtection degree;

    public DegreeOfProtection getDegree() {
        return degree;
    }

    @Override
    public void conversationStart() throws ValueException {
        try {
            degree = new DegreeOfProtection(questionInt(new Message(String.format(
                    "Select protection value (%d .. %d)",
                    DegreeOfProtection.getMinValue(),
                    DegreeOfProtection.getMaxValue()))
            ));
        } catch (WrongRangeException e) {
            logger.info("value out of rage");
            logger.info(e);
            throw new ValueException("out of protection degree rage");
        }
    }

    public DegreOfProtectionSelector(Function<Message, Message> speaker) throws ValueException{
        super(speaker);
        conversationStart();
    }
}
