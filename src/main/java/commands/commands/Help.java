package commands.commands;

import commands.Command;
import commands.CommandManager;
import console.ConsoleOutputHandler;
import core.ApplicationStatus;
import dataLayer.collection.TreeSetCollectionManager;
import dataLayer.models.Movie;

import java.util.Map;
import java.util.TreeSet;

public class Help extends Command {
    private final ConsoleOutputHandler coh;
    private final CommandManager commandManager;

    public Help(ConsoleOutputHandler coh, CommandManager commandManager){
        super("help", "вывести справку по доступным командам");
        this.coh = coh;
        this.commandManager = commandManager;
    }

    @Override
    public ApplicationStatus execute(String arg){
        Map<String, Command> commandMap = commandManager.getCommandMap();
        for (Command command : commandMap.values()) {
            String res = command.toString();
            coh.printLine(res);
        }
        coh.printLine("Список команд отображен");
        return ApplicationStatus.RUNNING;
    }
}
