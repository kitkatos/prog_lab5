package console.movieInput;

import console.ConsoleOutputHandler;
import console.exception.*;

public class MovieInputChecker {
    private final ConsoleOutputHandler coh;
    public MovieInputChecker(ConsoleOutputHandler coh) {
        this.coh = coh;
    }

    public <T> T readAndValidate(ThrowingSupplier<String> reader, ThrowingFunction<String, T> validator){
        while (true) {
            try {
                String input = reader.get();
                return validator.apply(input);
            } catch (EmptyInputException | OutOfRangeException | InvalidFormatException e) {
                coh.printError(e.getMessage());
            } catch (Exception e) {
                coh.printError("Неизвестная ошибка: " + e.getMessage());
            }

        }
    }
}
