package training.collector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.ammunition.ammunition.HamletType;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractCollector {

    protected static final Logger logger = LogManager.getLogger(AbstractCollector.class);

    public abstract void conversationStart() throws ValueException;

    private Function<Message, Message> speaker;


    protected static final List<String> genders = Arrays.stream(Gender.values())
            .map(Enum::toString)
            .collect(Collectors.toList());
    protected static final List<String> sizes = Arrays.stream(Size.values())
            .map(Enum::toString)
            .collect(Collectors.toList());
    protected static final List<String> hamletTypes = Arrays.stream(HamletType.values())
            .map(Enum::toString)
            .collect(Collectors.toList());

    protected Function<Message, Message> getSpeaker() {
        return speaker;
    }

    protected Message question(Message message) {
        Message answer = speaker.apply(message);
        if (answer == null)
            throw new NullPointerException();
        return answer;
    }

    protected int questionInt(Message message) throws ValueException {
        Message answer = question(message);
        int result = Integer.valueOf(answer.getMain());

        if (message.getVariables() != null && message.getVariables().size() > 0
                && (result > message.getVariables().size() || result < 0)
        ) {
            logger.info(String.format("wrong data returned %d", result));
            throw new ValueException("Out of rage");
        }
        return result;
    }

    protected double questionDouble(Message message) throws ValueException {
        Message answer = question(message);
        return Double.valueOf(answer.getMain());
    }

    protected boolean questionBool(Message message) throws ValueException {
        Message answer = question(message);
        return answer.getMain() != null && answer.getMain().equals("y");
    }

    protected void say(Message message){
        speaker.apply(message);
    }

    protected AbstractCollector(Function<Message, Message> speaker) {
        this.speaker = speaker;
    }

}
