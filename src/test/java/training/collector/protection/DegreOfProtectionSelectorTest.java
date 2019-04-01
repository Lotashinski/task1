package training.collector.protection;

import org.junit.Test;
import training.collector.AbstractCollectorTest;
import training.collector.Message;
import training.collector.ValueException;

import static org.junit.Assert.fail;

public class DegreOfProtectionSelectorTest extends AbstractCollectorTest {
    @Test
    public void conversationStart() {
        answer = "1";
        try {
            this.collector = new DegreOfProtectionSelector(message -> new Message(answer));
        } catch (ValueException e) {
            fail("initialization error");
        }
        super.conversationStart();
        answer = "-1";
        super.conversationStartWrong();
    }


}