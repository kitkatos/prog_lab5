package commands.commands;

import commands.Command;
import console.ConsoleOutputHandler;
import core.ApplicationStatus;
import dataLayer.collection.CollectionManager;
import dataLayer.models.Movie;
import file.FileWriter;
import file.ParserXML;
import java.io.IOException;
import java.util.TreeSet;

public class Save extends Command {
    CollectionManager collection;
    ConsoleOutputHandler console;
    ParserXML parser;
    FileWriter writer;
    public Save(CollectionManager collection, ConsoleOutputHandler console, ParserXML parser, FileWriter writer){
        super("save", "сохранить коллекцию в файл");
        this.collection = collection;
        this.console = console;
        this.parser = parser;
        this.writer = writer;
    }
    @Override
    public ApplicationStatus execute(String arg) throws Exception{
        try {
            console.printLine("Выполнение сохранения");
            console.printLine(arg);
            TreeSet<Movie> movieCollection = collection.getCollection();
            console.printLine("Коллекция получена");
            String xmlLine = parser.convertCollectionToXMLString(movieCollection);
            console.printLine(xmlLine);
            writer.writeToFile(xmlLine, arg);
            console.printLine("Коллекция сохранена");
            return ApplicationStatus.RUNNING;
        } catch (IOException e) {
            console.printError("Ошибка FileWriter: " + e.getMessage());
            return ApplicationStatus.ERROR;
        }
    }
}
