package commands.commands;

import commands.Command;
import console.ConsoleOutputHandler;
import core.ApplicationStatus;
import dataLayer.collection.CollectionManager;

public class Clear extends Command {
    CollectionManager collection;
    ConsoleOutputHandler console;
    public Clear(CollectionManager collection, ConsoleOutputHandler console){
        super("clear", "Очистить коллекцию");
        this.collection = collection;
        this.console = console;
    }
    @Override
    public ApplicationStatus execute(String arg){
        if (collection.getCollection().size() == 0 ) {
            console.printLine("Коллекция уже пуста");
        } else {
            collection.deleteAllElem();
            console.printLine("Коллекция успешно очищена");
        }
        return ApplicationStatus.RUNNING;
    }
}
