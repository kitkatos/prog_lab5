package commands.commands;

import commands.Command;
import console.ConsoleOutputHandler;
import core.ApplicationStatus;
import dataLayer.collection.TreeSetCollectionManager;
import dataLayer.models.Movie;

public class MinByCreationDate extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;


    public MinByCreationDate(TreeSetCollectionManager manager, ConsoleOutputHandler coh){
        super("min_by_creation_date", "вывести любой объект из коллекции, значение поля creationDate которого является минимальным");
        this.manager = manager;
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        if (manager.getCollection().isEmpty()) {
            coh.printLine("Коллекция пуста");
        } else {
            Movie elem = manager.getElemWithMinCreationDate();
            coh.printLine(elem.toString());
        }
        return ApplicationStatus.RUNNING;
    }
}
