package training;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.collector.AbstractCollector;
import training.collector.Collector;
import training.collector.Message;
import training.collector.ammunition.JacketCreate;
import training.collector.ammunition.PantsCreate;
import training.collector.motorcyclist.PriceListCollector;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Использовать возможности ООП: классы, наследование, полиморфизм, инкапсуляция. +
 * При кодировании должны быть использованы соглашения об оформлении кода java code convention. +
 * Классы должны быть грамотно разложены по пакетам. +
 * Проверка работоспособности через тесты. +
 * Для хранения параметров инициализации можно использовать файлы.
 * Использовать механизм логгирования. +
 * Использовать библиотеки сборки. +
 * <p>
 * Мотоциклист.
 * Определить иерархию амуниции. +
 * Экипировать мотоциклиста.
 * Посчитать стоимость. +
 * Провести сортировку амуниции на основе веса. +
 * Найти элементы амуниции, соответствующие заданному диапазону параметров цены. +
 */

public class App {

    static final Logger rootLogger = LogManager.getRootLogger();

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            rootLogger.info("App start");
            AbstractCollector collector = new PriceListCollector(message -> {
                StringBuffer buffer = new StringBuffer();
                buffer.append(String.format("%s%s\n", message.getMain(), message.getVariables().size() > 0 ? ":" : ""));
                for (int i = 0; i < message.getVariables().size(); ++i)
                    buffer.append(String.format("%3d: %s\n", i, message.getVariables().get(i)));
                System.out.println(buffer.toString());
                return new Message(scanner.next());
            });
            collector.conversationStart();

        } catch (Exception ex) {
            rootLogger.error(ex);
            Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).forEach(rootLogger::fatal)
            ;
        } finally {
            rootLogger.info("App close");
        }
    }
}
