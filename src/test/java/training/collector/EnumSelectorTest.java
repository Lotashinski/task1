package training.collector;

import org.junit.Test;
import training.ammunition.clothing.Size;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


public class EnumSelectorTest extends AbstractCollectorTest {


    public EnumSelectorTest() {
            List<String> variables = Arrays.stream(Size.values())
                    .map(Enum::toString)
                    .collect(Collectors.toList());
            this.collector = new EnumSelector(
                    message -> new Message(answer), variables, "");
    }

    @Test
    public void conversationStart() {
        answer = "0";
        super.conversationStart();
        assertEquals(0, ((EnumSelector) this.collector).getValue());
    }
}