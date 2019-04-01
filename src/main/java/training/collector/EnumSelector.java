package training.collector;

import java.util.List;
import java.util.function.Function;

public class EnumSelector extends AbstractCollector {

    private List<String> variables;

    private int value;

    private String message;

    public int getValue() {
        return value;
    }

    @Override
    public void conversationStart() throws ValueException {
        this.value = questionInt(new Message(message, variables));
    }

    public EnumSelector(Function<Message, Message> speaker, List<String> variables, String message) throws ValueException{
        super(speaker);
        this.variables = variables;
        this.message = message;
        conversationStart();
    }
}
