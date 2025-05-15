package commands.commands;

import commands.Command;
import commands.CommandManager;
import console.ConsoleOutputHandler;
import console.ConsoleParser;
import console.ExecuteArgs;
import core.ApplicationStatus;
import dataLayer.collection.CollectionManager;
import file.FileReader;

import java.io.IOException;
import java.util.List;

public class ExecuteScript extends Command {
    CollectionManager collection;
    ConsoleOutputHandler console;
    FileReader reader;
    CommandManager commandManager;
    ConsoleParser parser;
    public ExecuteScript(CollectionManager collection, ConsoleOutputHandler console, FileReader reader, CommandManager commandManager, ConsoleParser parser){
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.collection = collection;
        this.console = console;
        this.reader = reader;
        this.commandManager = commandManager;
        this.parser = parser;
    }
    @Override
    public ApplicationStatus execute(String arg){
        try {
            List<String> script = reader.readFile(arg);
            for (String line : script) {
                ExecuteArgs args = parser.getRequest(line);
                console.printLine("Выполнена команда");
                ApplicationStatus status = commandManager.executeCommand(args);
                if (status == ApplicationStatus.EXIT) {
                    return ApplicationStatus.EXIT;
                }
            }
            return ApplicationStatus.RUNNING;
        } catch (IOException e) {
            console.printError("Ошибка FileReader: " + e.getMessage());
            return ApplicationStatus.ERROR;
        }
    }
}
