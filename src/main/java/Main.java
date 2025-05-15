
import commands.Command;
import console.movieInput.MovieFactory;
import console.movieInput.MovieInputChecker;
import console.movieInput.MovieInputReader;
import console.movieInput.MovieInputValidator;
import dataLayer.collection.TreeSetCollectionManager;
import commands.CommandManager;
import console.*;
import core.Core;
import dataLayer.models.Movie;
import file.FileReader;
import file.FileWriter;
import file.ParserXML;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ConsoleInputHandler cih = new ConsoleInputHandler();
        ConsoleOutputHandler coh = new ConsoleOutputHandler();
        MovieInputReader movieReader = new MovieInputReader(cih, coh);
        MovieInputValidator movieValidator = new MovieInputValidator();
        MovieInputChecker movieChecker = new MovieInputChecker(coh);
        ConsoleParser parser = new ConsoleParser();
        ParserXML xmlParser = new ParserXML(coh);
        FileWriter writer = new FileWriter();
        FileReader reader = new FileReader();

        TreeSet<Movie> xmlMovie;

        try {
            List<String> file = reader.readFile("MovieCollection.xml");
            xmlMovie = xmlParser.getCollectionFromXML(file);
        } catch (IOException e) {
            coh.printError("Ошибка работы FileReader: " + e.getMessage());
            xmlMovie = new TreeSet<Movie>();
        } catch (Exception e){
            coh.printError("Какаято обшика:" + e.getMessage());
            xmlMovie = new TreeSet<Movie>();
        }

        TreeSet<Movie> TreeSetCollection = xmlMovie;
        Map<String, Command> commandMap = new HashMap<String, Command>();
        ArrayDeque<String> history = new ArrayDeque<String>();

        TreeSetCollectionManager collection = new TreeSetCollectionManager(TreeSetCollection);
        CommandManager commands = new CommandManager(commandMap, history, coh);
        MovieFactory factory = new MovieFactory(movieReader, movieValidator, movieChecker);

        Core core = new Core(collection, commands, cih, coh, factory, parser, xmlParser, writer, reader);

        core.start();
    }
}
