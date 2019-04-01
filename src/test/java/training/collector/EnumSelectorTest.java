package training.collector;

import org.junit.Test;
import training.ammunition.clothing.Gender;
import training.ammunition.clothing.Size;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class EnumSelectorTest extends AbstractCollectorTest {


    public EnumSelectorTest() {
        try {
            answer = "0";
            List<String> variables = Arrays.stream(Gender.values())
                    .map(Enum::toString)
                    .collect(Collectors.toList());
            this.collector = new EnumSelector(
                    message -> new Message(answer), variables, "");
        }catch (ValueException ex)
        {
            fail("inicialization error");
        }
    }

    @Test
    public void conversationStart() {
        super.conversationStart();
        assertEquals(0, ((EnumSelector) this.collector).getValue());
    }
}