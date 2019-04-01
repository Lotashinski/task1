package training.collector.ammunition;

import org.junit.Test;
import training.ammunition.WrongRangeException;
import training.ammunition.ammunition.Jacket;
import training.ammunition.ammunition.Pants;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;
import training.collector.AbstractCollectorTest;
import training.collector.Message;
import training.collector.ValueException;

import static org.junit.Assert.*;

public class JacketCreateTest extends AbstractCollectorTest {
    private static final String[] answers = {
            "10.0",
            "10.0",
            "name",
            "0",
            "0",
            "y"
    };

    private static int i;

    private static Message answer() {
        return new Message(answers[i++]);
    }

    @Test
    public void getPants() {
        try {
            i = 0;
            this.collector = new JacketCreate(message -> answer());
            collector.conversationStart();
            Jacket pants = new Jacket(10, 10, "name", Gender.MALE, Size.EXTRA_EXTRA_LAGRE, true);
            assertTrue(((JacketCreate)collector).getJacket().equals(pants));
        }catch (WrongRangeException ex)
        {
            fail();
        }catch (ValueException ex)
        {
            fail();
        }
    }

}