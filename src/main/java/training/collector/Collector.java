package training.collector;

import java.util.ArrayList;
import java.util.function.Function;

public class Collector extends AbstractCollector{
    @Override
    public void conversationStart() {
        question( new Message("efwefewf"));
    }

    public Collector(Function<Message, Message> speaker) {
        super(speaker);
    }
}
