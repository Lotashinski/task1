package training.collector.ammunition;

import org.junit.Test;
import training.ammunition.WrongRangeException;
import training.ammunition.ammunition.Pants;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;
import training.collector.AbstractCollectorTest;
import training.collector.Message;
import training.collector.ValueException;

import static org.junit.Assert.*;

public class PantsCreateTest extends AbstractCollectorTest {

    private static final String[] answers = {
            "10.0",
            "10.0",
            "name",
            "0",
            "0"
    };

    private static int i;

    private static Message answer() {
        return new Message(answers[i++]);
    }

    @Test
    public void getPants() {
        try {
            i = 0;
            this.collector = new PantsCreate(message -> answer());
            collector.conversationStart();
            Pants pants = new Pants(10, 10, "name", Gender.MALE, Size.EXTRA_EXTRA_LAGRE);
            assertTrue(((PantsCreate)collector).getPants().equals(pants));
        }catch (WrongRangeException ex)
        {
            fail();
        }catch (ValueException ex)
        {
            fail();
        }
    }
}