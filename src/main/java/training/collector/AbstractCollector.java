package training.collector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

public class AbstartCollector {
    private static final Logger logger = LogManager.getLogger(Collector.class);
    protected Function<String, String> speaker;

    public AbstartCollector(Function<String, String> speaker) {
        this.speaker = speaker;
    }
}
