package core;

import commands.*;
import commands.commands.*;
import console.*;
import console.movieInput.MovieFactory;
import dataLayer.collection.*;
import dataLayer.models.Coordinates;
import dataLayer.models.Movie;
import file.FileReader;
import file.FileWriter;
import file.ParserXML;



public class Core {
    private final TreeSetCollectionManager collectionManager;
    private final CommandManager commands;
    private final ConsoleInputHandler cih;
    private final ConsoleOutputHandler coh;
    private final MovieFactory factory;
    private final ConsoleParser parser;
    private final ParserXML xmlParser;
    private final FileWriter writer;
    private final FileReader reader;

    public Core(TreeSetCollectionManager collectionManager, CommandManager commands, ConsoleInputHandler cih,
                ConsoleOutputHandler coh, MovieFactory factory, ConsoleParser parser, ParserXML xmlParser, FileWriter writer, FileReader reader) {
        this.collectionManager = collectionManager;
        this.commands = commands;
        this.cih = cih;
        this.coh = coh;
        this.factory = factory;
        this.parser = parser;
        this.xmlParser = xmlParser;
        this.writer = writer;
        this.reader = reader;
    }

    public void start() {
        addCommands();

        coh.printLine("Добро пожаловать в приложение 'Movie collectionManager CLI'!");
        coh.printLine("Введите 'help' для просмотра доступных команд.");
            

        while (true) {
            try {
                coh.printString("> ");
                String input = cih.readTrimLine();
                ExecuteArgs args = parser.getRequest(input);
                if (args.command().isEmpty()) continue;

                ApplicationStatus status = commands.executeCommand(args);
                if (status == ApplicationStatus.EXIT) {
                    break;
                }
            } catch (Exception e) {
                coh.printError("Ошибка: " + e.getMessage());
            }
        }
    }

    private void addCommands() {
        commands.addCommand(new Help(coh, commands));
        commands.addCommand(new Info(collectionManager, coh));
        commands.addCommand(new Show(collectionManager, coh));
        commands.addCommand(new Add(collectionManager, coh, factory));
        commands.addCommand(new Update(collectionManager, coh, commands, factory));
        commands.addCommand(new RemoveById(collectionManager, coh));
        commands.addCommand(new Clear(collectionManager, coh));
        commands.addCommand(new Exit(coh));
        commands.addCommand(new AddIfMax(collectionManager, coh, factory));
        commands.addCommand(new RemoveGreater(collectionManager, coh, factory));
        commands.addCommand(new MinByCreationDate(collectionManager, coh));
        commands.addCommand(new MaxById(collectionManager, coh));
        commands.addCommand(new FilterWithName(collectionManager, coh));

        commands.addCommand(new History(collectionManager, coh, commands));
        commands.addCommand(new Save(collectionManager, coh, xmlParser, writer));
        commands.addCommand(new ExecuteScript(collectionManager, coh, reader, commands, parser));

    }
}
