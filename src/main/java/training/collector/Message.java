package training.collector;

import java.util.ArrayList;
import java.util.List;

public final class Message {
    private String main;

    private List<String> variables;

    public String getMain() {
        return main;
    }

    public Message() {
    }

    public List<String> getVariables() {
        return variables;
    }

    public Message(String main, List<String> variables) {
        this.main = main;
        this.variables = variables;
    }

    public Message(String main) {
        this(main, new ArrayList<String>());
    }
}
