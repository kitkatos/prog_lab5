package commands.commands;

import commands.Command;
import console.ConsoleOutputHandler;
import core.ApplicationStatus;
import dataLayer.collection.TreeSetCollectionManager;
import dataLayer.models.Movie;

public class MaxById extends Command {
    private final TreeSetCollectionManager manager;
    private final ConsoleOutputHandler coh;


    public MaxById(TreeSetCollectionManager manager, ConsoleOutputHandler coh){
        super("max_by_id", "вывести любой объект из коллекции, значение поля id которого является максимальным");
        this.manager = manager;
        this.coh = coh;
    }

    @Override
    public ApplicationStatus execute(String arg){
        if (manager.getCollectionSize() == 0) {
            coh.printLine("Коллекция пуста");
        } else {
            Movie elem = manager.getElemWithMaxId();
            coh.printLine(elem.toString());
        }
        return ApplicationStatus.RUNNING;
    }
}
