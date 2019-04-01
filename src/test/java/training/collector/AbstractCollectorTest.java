package training.collector;

import static org.junit.Assert.*;

public abstract class AbstractCollectorTest {

    public AbstractCollector collector;

    public String answer;


    public void conversationStart() {
        try {
            collector.conversationStart();
            assertTrue(true);
        } catch (ValueException e) {
            fail("wrong exception");
        }
    }

    public void conversationStartWrong() {
        try {
            collector.conversationStart();
            fail("exception don`t work");
        } catch (ValueException e) {
            assertTrue(true);
        }
    }
}